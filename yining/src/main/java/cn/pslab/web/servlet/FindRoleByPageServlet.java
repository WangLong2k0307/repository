package cn.pslab.web.servlet;

import cn.pslab.domain.PageBean;
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

@WebServlet("/findRoleByPageServlet")
public class FindRoleByPageServlet extends HttpServlet {

    private RoleService roleService = new RoleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        //1.接受参数
        String currentPageStr=request.getParameter("currentPage");
        String pageSizeStr=request.getParameter("pageSize");
        //接收rname
        String rname=request.getParameter("rname");
        if(rname!=null){
            rname=new String(rname.getBytes("ISO-8859-1"),"utf-8");
        }else {

        }

        int currentPage=0;//当前页码
        if (currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }

        int pageSize=0;//显示条数
        if (pageSizeStr!=null && pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=3;
        }

        //3.调用service查询PageBean对象
        PageBean<Role> pb=roleService.pageQuery(currentPage,pageSize,rname);
//        System.out.println(pb);
        //4.将pageBean对象序列化为json，返回
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),pb);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
