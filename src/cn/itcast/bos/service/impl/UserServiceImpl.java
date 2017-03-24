package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * luopa 在 2017/3/14 创建.
 */

@Controller
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(User model) {
        String password = MD5Utils.md5(model.getPassword());
        List<User> userList = (List<User>) userDao.findByNamedQuery("findByUsernameAndPassword", model.getUsername(), password);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void editPwd(String password, String id) {
        password = MD5Utils.md5(password);
        userDao.executeNamedQuery("editPassword", password, id);
    }

    @Override
    public User oldPwd(String password, String username) {
        password = MD5Utils.md5(password);
        List<User> userList = (List<User>) userDao.findByNamedQuery("findByUsernameAndPassword", username, password);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}
