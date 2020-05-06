package cn.pslab.wanglong.web.servlet;

import cn.pslab.wanglong.domain.ResultInfo;
import cn.pslab.wanglong.domain.User;
import cn.pslab.wanglong.service.UserService;
import cn.pslab.wanglong.service.impl.UserServiceImpl;
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
    //声明userService业务对象
    private UserService service = new UserServiceImpl();
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //为了保证验证码不复用
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //将json数据写回前端
            //设置content-type类型
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();

        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4、响应结果
        if (flag){
            //注册成功
            info.setFlag(true);

        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        //将info对象序列化为json并返回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json数据写回前端
        //设置content-type类型
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


}

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //为了保证验证码不复用
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误

            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //将json数据写回前端
            //设置content-type类型
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1、获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();
        //2、封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User u = service.login(user);

        //判断用户对象是否为null
        if (u == null){
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if (u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        if (u != null && "Y".equals(u.getStatus())){
            //登录成功标记,便于findUserServlet使用
            request.getSession().setAttribute("user",u);
            //登录成功
            info.setFlag(true);
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);

    }

    /**
     * 登陆后展示姓名的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");

        /*//将user写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);*/
        writeValue(user, response);
    }

    /**
     * 邮箱激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、获取激活码
        String code = request.getParameter("code");
        //判断code是否为空
        if (code != null) {
            //2、调用service完成激活
            boolean flag = service.active(code);
            //3、判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='http://localhost:8075/wanglong/login.html'>登录</a>";
            } else {
                msg = "激活失败，请联系管理员！";
            }
            //设置编码方式
            response.setContentType("text/html;charset=utf-8");
            //将msg传递给客户端
            response.getWriter().write(msg);
        }

    }

    /**
     * 退出登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、销毁session
        request.getSession().invalidate();
        //2、跳转到登录页面(重定向需要用虚拟路径)
        response.sendRedirect(request.getContextPath()+"/login.html");

    }


}
