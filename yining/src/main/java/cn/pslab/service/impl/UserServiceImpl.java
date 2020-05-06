package cn.pslab.service.impl;

import cn.pslab.dao.UserDao;
import cn.pslab.dao.impl.UserDaoImpl;
import cn.pslab.domain.PageBean;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;
import cn.pslab.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {

        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            return false;
        }

        //保存用户信息
        userDao.save(user);
        return true;
    }

    //登录
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public PageBean<User> pageQuery(String username, int currentPage, int pageSize, String name, String orderby) {
        //封装PageBean
        PageBean<User> pb = new PageBean<User>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = userDao.findTotalCount(username,name);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<User> list = userDao.findByPage(username,start,pageSize,name,orderby);

        pb.setList(list);


        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public void dropchild(int uid) {

        userDao.dropchild(uid);

    }

    //显示子用户信息时，根据子用户id进行查询
    @Override
    public User findByUid(int suid) { return userDao.findByUid(suid); }

    //根据rid查询权限
    @Override
    public Role checkByRid(int rid) { return userDao.checkByRid(rid); }

    //根据name修改
    @Override
    public void updateByName(int suid,String value, String name) {
        userDao.updateByName(suid,value,name);
    }

}
