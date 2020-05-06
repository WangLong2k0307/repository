package cn.pslab.web.servlet;

import cn.pslab.dao.DataDao;
import cn.pslab.dao.MonitorDao;
import cn.pslab.dao.impl.DataDaoImpl;
import cn.pslab.dao.impl.MonitorDaoImpl;
import cn.pslab.domain.*;
import cn.pslab.service.DataService;
import cn.pslab.service.MonitorService;
import cn.pslab.service.impl.DataServiceImpl;
import cn.pslab.service.impl.MonitorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/monitor/*")
public class MonitorServlet extends BaseServlet {
    //声明monitorservice业务对象
    private MonitorService monitorService = new MonitorServiceImpl();
    private DataService dataService = new DataServiceImpl();
    private DataDao dataDao = new DataDaoImpl();
    private MonitorDao monitorDao = new MonitorDaoImpl();
    /**
     * 查询设备记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用service完成查询
        //设置日期
        monitorDao.setEqTime();
        List<Equipment> equipments = monitorService.findEquipment();

        //2.将list序列化为json返回
        writeValue(equipments,response);
        System.out.println(equipments);

    }

    public void findCurrent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String cid = request.getParameter("c_id");
        //2.调用service完成查询
        List<Current> currents = monitorService.findCurrent(Integer.parseInt(cid));
        //3.将current序列化为json返回
        writeValue(currents,response);
        System.out.println(currents);
    }

    public void findVoltage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String vid = request.getParameter("v_id");
        //2.调用service完成查询
        List<Voltage> voltages = monitorService.findVoltage(Integer.parseInt(vid));
        //3.将current序列化为json返回
        writeValue(voltages,response);
        System.out.println(voltages);
    }

    public void findEle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String id = request.getParameter("id");
        //2.调用service完成查询
        List<Current> currents = new ArrayList<>();
        List<Voltage> voltages = new ArrayList<>();
        List[] ele = new ArrayList[2];
        currents = monitorService.findCurrent(Integer.parseInt(id));
        voltages = monitorService.findVoltage(Integer.parseInt(id));
        ele[0] = voltages;
        ele[1] = currents;

        //3.将current序列化为json返回
        writeValue(ele,response);
        System.out.println(currents);
        System.out.println(voltages);
        System.out.println(ele);
    }

    public void findData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //1.调用service
        DataBean datas = dataService.findData();

        //两周内日期统计
        //1.获取当天的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String today = df.format(new Date());// new Date()为获取当前系统时间
        //2.获取第前14天的日期
        String begainD = dataService.getAnotherDay("yyyy-MM-dd", today, -13);
        //3.获取前14天每天的时间
        List<Date> dateList = dataService.findDates(begainD, today);
        datas.setTwoWeeksDays(dateList);

        //两周内报警数
        int counts[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String pd1 = "";
        String pd2 = "";
        for (int i = -13; i <=0; i++) {
            pd1 = dataService.getAnotherDay("yyyy-MM-dd", today, i);
            pd2 = dataService.getAnotherDay("yyyy-MM-dd", today, i+1);

            int weekCounts = dataDao.findWeekCounts(pd1,pd2);
            counts[13+i] = weekCounts;
        }
        datas.setTwoWeeksReportCounts(counts);

        //2.序列化json返回
        writeValue(datas,response);
        //检查数据
        System.out.println(datas.getAlarmRate());
        System.out.println(datas.getTwoWeeksDays());
        System.out.println(counts[13]);
    }

    public void findTipToday(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //1.获取当天的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String today = df.format(new Date());// new Date()为获取当前系统时间

        List<Alarm> tipToday = dataDao.findTipToday(today);
        writeValue(tipToday,response);
    }
}
