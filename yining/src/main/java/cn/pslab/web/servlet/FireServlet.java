package cn.pslab.web.servlet;

import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.PageBean;
import cn.pslab.service.FireLogService;
import cn.pslab.service.impl.FireLogServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fireServlet")
/**
 * 报警信息展示
 */
public class FireServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //获取参数
        String currentPageStr = request.getParameter("currentPage");//当前页码
        String pageSizeStr = request.getParameter("pageSize");//每页展示数据数
        String name = request.getParameter("name");
        String infor = request.getParameter("infor");
        String sta = request.getParameter("sta");
        //System.out.println(name);
        //System.out.println(pageSizeStr);
        int currentPage = 0;
        //当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        //每页显示条数，如果不传递，默认为5条
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 10;
        }
        FireLogService fireLogService = new FireLogServiceImpl();
        //调用service查询

        PageBean<Alarm> pb = fireLogService.pageQuery(currentPage, pageSize, name, infor, sta);

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), pb);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
