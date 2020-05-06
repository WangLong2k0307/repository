package cn.pslab.service;

import cn.pslab.domain.PageBean;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;

public interface UserService {
    //注册
    boolean regist(User user);

    //登录
    User login(User user);

    //根据用户名进行分页查询
    public PageBean<User> pageQuery(String username, int currentPage, int pageSize, String name, String orderby);

    //删除
    void dropchild(int uid);

    //根据uid查找用户
    User findByUid(int suid);

    //根据rid查找用户
    Role checkByRid(int rid);

    //根据name修改
    void updateByName(int suid,String value,String name);


}
