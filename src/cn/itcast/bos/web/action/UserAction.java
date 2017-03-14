package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * luopa 在 2017/3/13 创建.
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    public String login(){
        String checkcode = ServletActionContext.getRequest().getParameter("checkcode");
        Object key = ServletActionContext.getRequest().getSession().getAttribute("key");
        if ( StringUtils.isBlank(checkcode)||!checkcode.equals(key)) {
            this.addActionError(this.getText("checKeykError"));
            return "login";
        }else {

        }
        return "";
    }
}
