package cn.pslab.wanglong.dao;

import cn.pslab.wanglong.domain.User;

public interface UserDao {

    //根据username查询用户是否存在
    public User findByUsername(String username);

    //保存用户信息
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
