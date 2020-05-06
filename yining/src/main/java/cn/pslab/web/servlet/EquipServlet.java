package cn.pslab.web.servlet;

import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.service.EquipListService;
import cn.pslab.service.FireLogService;
import cn.pslab.service.impl.EquipListServiceImpl;
import cn.pslab.service.impl.FireLogServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/equipServlet")
public class EquipServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        EquipListService service = new EquipListServiceImpl();
        List<Equipment> equipment = service.query();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), equipment);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
