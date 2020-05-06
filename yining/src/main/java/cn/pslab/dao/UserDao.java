package cn.pslab.dao;

import cn.pslab.domain.Role;
import cn.pslab.domain.User;

import java.util.List;

public interface UserDao {

    /*根据用户名查询用户信息*/
    public User findByUsername(String username);

    /*用户保存*/
    public void save(User user);

    /*用户登录*/
    User findByUsernameAndPassword(String username, String password);

    /*根据username查询总记录数*/
    public int findTotalCount(String username,String name);

    /*根据username，start,pageSize查询当前页的数据集合*/
    public List<User> findByPage(String username, int start, int pageSize, String name, String orderby);

    /*根据用户名查询用户信息*/
    public void dropchild(int uid);

    /*根据uid查找用户*/
    User findByUid(int suid);

    /*根据rid查找权限*/
    Role checkByRid(int rid);

    //根据name修改
    public void updateByName(int suid,String value,String name);
}
