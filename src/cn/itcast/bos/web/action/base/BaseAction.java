package cn.itcast.bos.web.action.base;

import cn.itcast.bos.service.UserService;
import cn.itcast.bos.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * luopa 在 2017/3/13 创建.
 */

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;
    @Resource
    protected UserService userService;
    @Resource
    protected StaffService staffService;
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
        try {
            model=actualTypeArgument.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
