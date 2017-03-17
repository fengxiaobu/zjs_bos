package cn.itcast.bos.web.interceptor;

import cn.itcast.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * luopa 在 2017/3/15 创建.
 * 登录拦截
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    /**
     * Subclasses must override to implement the interceptor logic.
     *
     * @param invocation the action invocation
     * @return the result of invocation
     * @throws Exception
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        System.out.println("===============执行登录拦截器===============");
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        if (user == null) {
            return "login";
        }
        return invocation.invoke();
    }
}
