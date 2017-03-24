package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.web.action.base.BaseAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * luopa 在 2017/3/16 创建.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

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
        staffService.pageQuery(pageBean);
        this.writePageBeanToJSON(pageBean, new String[]{"decidedzones", "currentPage", "pageSize", "detachedCriteria"});
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

    /**
     * 查询取派员数据，返回json
     */
    public String findStaffByAjax(){
        //提供离线条件查询对象，包装查询条件
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        //添加过滤条件：删除标志deltag等于“0”
        dc.add(Restrictions.eq("deltag", "0"));
        //添加过滤条件：没有负责定区取派员
        dc.add(Restrictions.isEmpty("decidedzones"));
        List<Staff> list = staffService.findByCondition(dc);

        String[] excludes = new String[]{"decidedzones","standard","station","deltag","haspda","telephone"};
        this.writeListToJSON(list, excludes);
        return NONE;
    }
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setSid(String ids) {
        this.ids = ids;
    }
}
