package cn.itcast.bos.dao.impl;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.dao.base.BaseDaoImpl;
import cn.itcast.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * luopa 在 2017/3/13 创建.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findUserByUsername(String username) {
        List<User> list = (List<User>) this.getHibernateTemplate().find(" from User where username=?", username);
        if (list != null&&list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
