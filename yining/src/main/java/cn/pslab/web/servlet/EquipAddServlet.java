package cn.pslab.web.servlet;

import cn.pslab.domain.Equipment;
import cn.pslab.domain.ResultInfo;
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

@WebServlet("/equipAddServlet")
/**
 * 添加设备
 */
public class EquipAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EquipListService service = new EquipListServiceImpl();
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        Equipment equipment = new Equipment();
        try {
            BeanUtils.populate(equipment, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(equipment.getLatitude());
        boolean flag = service.equipAdd(equipment);
        ResultInfo info = new ResultInfo();
        //响应结果
        if (flag){
            //添加成功
            info.setFlag(true);

        }else {
            //添加失败
            info.setFlag(false);
            info.setErrorMsg("添加失败");
        }
        //将info对象序列化为json并返回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json数据写回前端
        //设置content-type类型
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
