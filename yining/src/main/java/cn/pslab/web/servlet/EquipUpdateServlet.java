package cn.pslab.web.servlet;

import cn.pslab.domain.Equipment;
import cn.pslab.service.EquipListService;
import cn.pslab.service.impl.EquipListServiceImpl;
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

@WebServlet("/equipUpdateServlet")
/**
 * 修改设备信息
 */
public class EquipUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转码防止页面出现乱码
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String eq_id = request.getParameter("eq_id");
        response.setContentType("text/html;charset=utf-8");
        EquipListService service = new EquipListServiceImpl();
        service.update(name, value, eq_id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
