package cn.itcast.bos.dao.impl;

import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.dao.base.BaseDaoImpl;
import cn.itcast.bos.domain.User;
import org.springframework.stereotype.Repository;

/**
 * luopa 在 2017/3/13 创建.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    public User login(User user){

        return null;
    }
}
