package cn.pslab.web.servlet;


import cn.pslab.domain.PageBean;
import cn.pslab.domain.ResultInfo;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;
import cn.pslab.service.UserService;
import cn.pslab.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //声明userservice的业务对象
    private UserService Userservice = new UserServiceImpl();

    /*注册功能*/
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();



        //从session中获取登录用户
        User fuser = (User) request.getSession().getAttribute("user");
        //封装对象
        User user = new User();
        if(fuser!=null){
            //登陆用户的uid
            user.setFusername(fuser.getUsername());
        }
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        boolean flag = Userservice.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名重复");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /*登录功能*/
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用Service查询
        User u  = Userservice.login(user);

        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }else {
            request.getSession().setAttribute("user",u);//登录成功标记
            //登录成功
            info.setFlag(true);
        }

        writeValue(info,response);
    }


    /*分页查询*/
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        User fuser = (User) request.getSession().getAttribute("user");
        String username=fuser.getUsername();
        String name = request.getParameter("name");

        String orderby = request.getParameter("orderby");

        //解决中文乱码
        name=new String(name.getBytes("iso-8859-1"),"utf-8");

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;//第一次访问无值，默认第一页
        }
        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 3;//每页显示条数，如果不传递，默认每页显示5条记录
        }

        //调用service查询PageBean对象
        PageBean<User> pb = Userservice.pageQuery(username, currentPage, pageSize,name,orderby);
        //将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    public void dropchild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("duid"));
        Userservice.dropchild(uid);
    }

    /*查找功能*/
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        writeValue(user,response);
    }

    /*查找功能*/
    public void findByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        int suid = Integer.parseInt(request.getParameter("suid"));
        //3.调用Service查询
        User suser  = Userservice.findByUid(suid);
        //将user写回客户端
        writeValue(suser,response);
    }

    //
    public void updateByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收参数
        int suid = Integer.parseInt(request.getParameter("suid"));
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        //解决中文乱码
        value=new String(value.getBytes("iso-8859-1"),"utf-8");

        Userservice.updateByName(suid,value,name);
    }

    /*查找权限功能*/
    public void checkByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        //获取rid
        int rid = user.getRid();
        //传递rid
        Role role  = Userservice.checkByRid(rid);
        //将user写回客户端
        writeValue(role,response);
    }

    /*退出功能*/
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath());
    }

}
