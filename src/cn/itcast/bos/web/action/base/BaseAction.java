package cn.itcast.bos.web.action.base;

import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * luopa 在 2017/3/13 创建.
 */

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;

    @Resource
    protected RegionService regionService;
    @Resource
    protected SubareaService subareaService;

    @Resource
    protected UserService userService;
    @Resource
    protected StaffService staffService;
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria;
    public BaseAction() {
        /**
         * 获得父类类型
         */
        ParameterizedType parameterizedType =(ParameterizedType) this.getClass().getGenericSuperclass();
        /**
         * 获得父类上的泛型数组
         */
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        //实例化类型
        Class<T> actualTypeArgument= (Class<T>) actualTypeArguments[0];
        //离线查询对象
        detachedCriteria = DetachedCriteria.forClass(actualTypeArgument);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model=actualTypeArgument.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setPage(Integer page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(Integer rows) {
        pageBean.setPageSize(rows);
    }

    @Override
    public T getModel() {
        return model;
    }

    public void writePageBeanToJSON(PageBean pageBean, String excluds[]) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excluds);
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        String json = jsonObject.toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
