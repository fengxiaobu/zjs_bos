package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * luopa 在 2017/3/22 创建.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Resource
    private CustomerService customerService;
    private String subareaid;// 属性驱动，分区id
    private Integer[] customerIds;//接收表单提交的客户id数组

    public void setCustomerIds(Integer[] customerIds) {
        this.customerIds = customerIds;
    }

    public void setSubareaid(String subareaid) {
        this.subareaid = subareaid;
    }

    public String pageQuery(){
        decidedzoneService.query(pageBean);
        String[] excludes = new String[] { "detachedCriteria", "pageSize",
                "currentPage", "subareas" ,"decidedzones"};
        this.writePageBeanToJSON(pageBean, excludes);
        return NONE;
    }

    public String save(){
        decidedzoneService.save(model,subareaid);
        return NONE;
    }

    /**
     * 关联CRM调用未关联到定区的客户
     * @return
             */
    public String findCustomersNoAssociation(){
        List<Customer> customers = customerService.findnoassociationCustomers();
        this.writeListToJSON(customers,new String[]{"station","telephone","address","decidedzone_id"});
        return NONE;
    }

    public String findCustomersHasAssociation(){
        List<Customer> customers = customerService.findhasassociationCustomers(model.getId());
        this.writeListToJSON(customers,new String[]{"station","telephone","address","decidedzone_id"});
        return NONE;
    }

    /**
     * 通过代理对象调用crm服务，将客户关联到指定定区
     */
    public String assigncustomerstodecidedzone(){
        customerService.assignCustomersToDecidedZone(customerIds, model.getId());
        return "list";
    }
}
