package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
public class RegionAction extends BaseAction<Region> {

    private File myFile;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    //批量导入区域
    public String importXLS() throws IOException {
        List<Region> list = new ArrayList<>();
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
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();

                String shortcode = province + city + district;
                String[] strings = PinYin4jUtils.getHeadByString(shortcode);
                shortcode = StringUtils.join(strings, "");
                String[] strings1 = PinYin4jUtils.stringToPinyin(city);
                String citycode = StringUtils.join(strings1, "");

                Region region = new Region(id, province, city, district, postcode, shortcode, citycode);
                list.add(region);
            }
            // 批量保存数据
            regionService.saveBatch(list);
        } catch (Exception e) {
            flag = "0";
        }
        ServletActionContext.getResponse().getWriter().print(flag);
        return NONE;
    }

    public String pageQuery() {
        regionService.pageQuery(pageBean);
        this.writePageBeanToJSON(pageBean, new String[]{"detachedCriteria",
                "pageSize", "currentPage", "subareas"});
        return NONE;
    }

    public String delete() {
        regionService.delete(ids);
        return "list";
    }

    public String findAll() {
        List<Region> regionList = regionService.finAll();

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subareas", "province", "city",
                "district", "postcode", "shortcode", "citycode"});
        JSONArray jsonArray = JSONArray.fromObject(regionList, jsonConfig);

        String json = jsonArray.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
