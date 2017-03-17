package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;

/**
 * luopa 在 2017/3/14 创建.
 */
public interface UserService {

    User login(User model);

    void editPwd(String password, String id);

    User oldPwd(String password, String username);
}
