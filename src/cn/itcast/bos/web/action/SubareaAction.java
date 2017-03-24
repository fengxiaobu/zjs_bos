package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * luopa 在 2017/3/18 创建.
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    private File myFile;
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String pageQuery() {
        String addresskey = model.getAddresskey();
        Region region = model.getRegion();
        DetachedCriteria subareaDC = pageBean.getDetachedCriteria();

        if (StringUtils.isNotBlank(addresskey)) {
            subareaDC.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        if (region != null) {
            DetachedCriteria regionDC = subareaDC.createCriteria("region");
            String province = region.getProvince();//省
            String city = region.getCity();//市
            String district = region.getDistrict();//区

            if (StringUtils.isNoneBlank(province)) {
                regionDC.add(Restrictions.like("province", "%" + province + "%"));
            }
            if (StringUtils.isNoneBlank(city)) {
                regionDC.add(Restrictions.like("city", "%" + city + "%"));
            }
            if (StringUtils.isNoneBlank(district)) {
                regionDC.add(Restrictions.like("district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.writePageBeanToJSON(pageBean, new String[]{"subareas",
                "decidedzone", "detachedCriteria", "pageSize", "currentPage"});
        return NONE;
    }

    public String importXLS() throws IOException {
        List<Subarea> list = new ArrayList<>();
        String flag = "1";
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(myFile));
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            for (Row row : sheet) {
                int rowNum = row.getRowNum();
                if (rowNum == 0) {
                    continue;
                }
                String id = row.getCell(0).getStringCellValue();
                String regionid = row.getCell(1).getStringCellValue();
                String addresskey = row.getCell(2).getStringCellValue();
                String startnum = row.getCell(3).getStringCellValue();
                String endnum = row.getCell(4).getStringCellValue();
                String single = row.getCell(5).getStringCellValue();
                String position = row.getCell(6).getStringCellValue();
                Region region = new Region();
                region.setId(regionid);
                Subarea subarea = new Subarea(id, region, addresskey, startnum, endnum, single, position);
                list.add(subarea);
            }
            // 批量保存数据
            subareaService.saveBatch(list);
        } catch (Exception e) {
            flag = "0";
        }
        ServletActionContext.getResponse().getWriter().print(flag);

        return NONE;
    }


    public String exportXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件，当前这个文件在内存中
        HSSFSheet sheet = workbook.createSheet("分区数据");// 创建一个sheet页
        HSSFRow headRow = sheet.createRow(0);// 创建标题行
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("区域编码");
        headRow.createCell(2).setCellValue("关键字");
        headRow.createCell(3).setCellValue("起始号");
        headRow.createCell(4).setCellValue("结束号");
        headRow.createCell(5).setCellValue("当双号");
        headRow.createCell(6).setCellValue("位置信息");
        List<Subarea> subareaList = subareaService.findSubareasByIDs(ids);
        for (Subarea subarea : subareaList) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
            dataRow.createCell(2).setCellValue(subarea.getAddresskey());
            dataRow.createCell(3).setCellValue(subarea.getStartnum());
            dataRow.createCell(4).setCellValue(subarea.getEndnum());
            dataRow.createCell(5).setCellValue(subarea.getSingle());
            dataRow.createCell(6).setCellValue(subarea.getPosition());
        }

        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        String fileName = "分区数据.xls";
        fileName = FileUtils.encodeDownloadFilename(fileName, ServletActionContext.getRequest().getHeader("user-agent"));
        ServletActionContext.getResponse().setContentType(ServletActionContext.getServletContext().getMimeType(fileName));

        ServletActionContext.getResponse().setHeader("content-disposition",
                "attachment;filename=" + fileName);
        workbook.write(outputStream);
        return NONE;
    }

    public String findSubareaByAjax(){
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        //添加过滤条件：未分配到定区的分区
        dc.add(Restrictions.isNull("decidedzone"));
        List<Subarea> list = subareaService.findByCondition(dc);
        String[] excludes = new String[]{"decidedzone","region","startnum","endnum","single"};
        this.writeListToJSON(list,excludes);
        return NONE;
    }
}
