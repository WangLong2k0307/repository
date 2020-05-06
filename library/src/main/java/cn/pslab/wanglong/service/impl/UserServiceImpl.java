package cn.pslab.wanglong.service.impl;

import cn.pslab.wanglong.dao.UserDao;
import cn.pslab.wanglong.dao.impl.UserDaoImpl;
import cn.pslab.wanglong.domain.User;
import cn.pslab.wanglong.service.UserService;
import cn.pslab.wanglong.util.MailUtils;
import cn.pslab.wanglong.util.UuidUtil;

public class UserServiceImpl implements UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */

    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        //1、根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if (u != null){
            //用户名存在，注册失败
            return false;
        }

        //2、保存用户信息
        //2.1、设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2、设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //3、激活邮件发送
        String content = "<a href='http://localhost:8075/wanglong/user/active?code="+user.getCode()+"'>点击激活【BSW图书网】</a>";

        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {

        //1、根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null){
            //2、调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }


    }

    /**
     * 登录功能
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
