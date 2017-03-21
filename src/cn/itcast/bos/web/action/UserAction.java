package cn.itcast.bos.web.action;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
            System.out.println("model = " + model.getUsername());
            User user = userService.login(model);
            if (user == null) {
                this.addActionError(this.getText("loginError"));
                return "login";
            } else {
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
                return "home";
            }
        }
    }

    //退出登录
    public String logOut() {
        ServletActionContext.getRequest().getSession()
                .invalidate();

        return "login";
    }

    //修改密码
    public String editPwd() throws IOException {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        String ms = "1";
        try {
            userService.editPwd(model.getPassword(), user.getId());
        } catch (Exception e) {
            ms = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(ms);
        return NONE;
    }

    public String oldPwd() throws IOException {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        String ms = "true";

        model.setUsername(user.getUsername());
        User exitUser = userService.login(model);
        if (exitUser == null) {
            ms = "false";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(ms);
        return NONE;
    }
}
