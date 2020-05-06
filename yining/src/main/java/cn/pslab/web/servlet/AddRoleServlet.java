package cn.pslab.web.servlet;

import cn.pslab.domain.ResultInfo;
import cn.pslab.domain.Role;
import cn.pslab.service.RoleService;
import cn.pslab.service.impl.RoleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addRoleServlet")
public class AddRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        Role role=new Role();
        try {
            BeanUtils.populate(role,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        RoleService service= new RoleServiceImpl();
        boolean flag=service.add(role);
        ResultInfo info=new ResultInfo();
        //4.响应结果
        if(flag){
            //成功
            info.setFlag(true);
        }else{
            //添加失败
            info.setFlag(false);
            info.setErrorMsg("角色名已存在");
        }

        //将info对象序列化为json
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(info);

        //数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
