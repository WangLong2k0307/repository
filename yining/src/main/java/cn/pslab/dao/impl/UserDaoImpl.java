package cn.pslab.dao.impl;

import cn.pslab.dao.UserDao;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;
import cn.pslab.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from user where username = ? ";
            //2.执行sql
            user=template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into user(name,username,password,fusername,location,contact,contacttel,description) values(?,?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql,user.getName(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getFusername(),
                            user.getLocation(),
                            user.getContact(),
                            user.getContacttel(),
                            user.getDescription()
        );
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public int findTotalCount(String username, String name) {
        //定义sql模板(1=1是为了防止不传参，最后要加空格)
        String sql="select count(*) from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);//StringBuilder是可变字符串对象
        List params=new ArrayList();//条件们

        //判断参数是否有值
        if(username!=null){
            sb.append(" and fusername = ? ");
            params.add(username);//添加？的对应值
        }
        if(name!=null && name.length()>0){
            sb.append(" and name like ?");
            params.add("%"+name+"%");
        }

        sql=sb.toString();//sql重新赋值

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(String username, int start, int pageSize, String name, String orderby) {
        String sql = "select * from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();//条件们

        //按父账号查询
        if(username!=null && username.length()>0){
            sb.append(" and fusername = ? ");
            params.add(username);//添加？的对应值
        }
        //按查询条件查询
        if(name!=null && name.length()>0){
            sb.append(" and name like ? ");
            params.add("%"+name+"%");
        }


        //查看是否排序
        if(orderby!=null && orderby.length()>0){
            String[] order = orderby.split("_");
            sb.append(" ORDER BY "+order[0]+" "); //此处不能用？传参，可能是格式不对
            if(order[1].equals("desc")){
                //查看正逆序
                sb.append(" desc");
            }
        }



        sb.append(" limit ? , ? ");//分页条件
        params.add(start);
        params.add(pageSize);

        sql=sb.toString();//sql重新赋值

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

    @Override
    public void dropchild(int uid) {
        try {
            //1.定义sql
            String sql = "delete from user where uid = ? ";
            //2.执行sql
            template.update(sql,uid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUid(int suid) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from user where uid = ? ";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), suid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Role checkByRid(int rid) {
        Role role = null;
        try {
            //1.定义sql
            String sql = "select * from role where rid = ? ";
            //2.执行sql
            role = template.queryForObject(sql, new BeanPropertyRowMapper<Role>(Role.class), rid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public void updateByName(int suid,String value, String name) {
        try {
            //1.定义sql
            String sql = "update user set "+name+"=\'"+value+"\' where uid=?";  //此处不能用？传参，可能是格式不对

            //2.执行sql
            template.update(sql,suid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
