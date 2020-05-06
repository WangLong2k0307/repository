package cn.pslab.web.servlet;

import cn.pslab.domain.Equipment;
import cn.pslab.service.EquipListService;
import cn.pslab.service.impl.EquipListServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/equipDetailServlet")
/**
 * 修改时查询设备的详细信息
 */
public class equipDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eq_id = request.getParameter("eq_id");
        EquipListService service = new EquipListServiceImpl();
        Equipment equipment = service.findOne(eq_id);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), equipment);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
