package cn.pslab.web.servlet;

import cn.pslab.domain.Role;
import cn.pslab.service.RoleService;
import cn.pslab.service.impl.RoleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/perRoleServlet")
public class PerRoleServlet extends HttpServlet {
    private RoleService roleService=new RoleServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收id
        String rid=request.getParameter("rid");
        //调用service查询product对象
        Role role=roleService.findOne(rid);

        //转为json写回客户端
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),role);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
