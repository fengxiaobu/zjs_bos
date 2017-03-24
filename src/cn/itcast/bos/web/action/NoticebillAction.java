package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * luopa 在 2017/3/24 创建.
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    private String phone;
    @Resource
    private CustomerService customerService;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String findCustomerByPhone() {
        Customer customer = customerService.findCustomerByPhone(phone);
        String[] excludes = new String[]{"decidedzone_id"};
        this.writeObjectToJSON(customer, excludes);
        return NONE;
    }

    public String save(){
        User user= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        model.setUser(user);
        noticebillService.save(model);
        return "toSave";
    }
}
