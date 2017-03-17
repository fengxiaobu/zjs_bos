package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * luopa 在 2017/3/16 创建.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    private Integer page;
    private Integer rows;
    private String ids;

    public String save() {
        staffService.save(model);
        return "list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);

        staffService.pageQuery(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage", "pageSize", "detachedCriteria"});
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        System.out.println("jsonObject = " + jsonObject.toString());
        ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
        return NONE;
    }

    //修改数据
    public String update() {
        //先查询原始数据，再进行数据覆盖
        Staff staff = staffService.findByID(model.getId());
        //进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setStation(model.getStation());
        staff.setHaspda(model.getHaspda() != null ? model.getHaspda() : "0");

        staff.setStandard(model.getStandard());
        staffService.update(staff);
        return "list";
    }

    //作废
    public String delete() {
        staffService.delete(ids);
        return "list";
    }
//还原
    public  String restore(){
        staffService.restore(ids);
        return "list";
    }
    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setSid(String ids) {
        this.ids = ids;
    }
}
