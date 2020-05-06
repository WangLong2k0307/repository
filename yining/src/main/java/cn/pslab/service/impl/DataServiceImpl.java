package cn.pslab.service.impl;

import cn.pslab.dao.DataDao;
import cn.pslab.dao.impl.DataDaoImpl;
import cn.pslab.domain.DataBean;
import cn.pslab.service.DataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataServiceImpl implements DataService {

    private DataDao dataDao = new DataDaoImpl();

    @Override
    public DataBean findData() {
        //1.封装DataBean
        DataBean db = new DataBean();
        //获取当天的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间

        //2.设置数据
        //设备数
        int eqCount = dataDao.findEqCount();
        db.setEqCount(eqCount);
        //单位数
        int depCount = dataDao.findDepCount();
        db.setDepCount(depCount);
        //报警比例
        int a = dataDao.findAlarm(time);
        int alarm = (a * 100)/eqCount;
        db.setAlarmRate(alarm);
        //故障比例
        int f = dataDao.findFail(time);
        int fail = (f * 100)/eqCount;
        db.setFailRate(fail);



        //当前报警数
        int alarmTotal = dataDao.alarmTotal();
        db.setAlarmTotal(alarmTotal);
        //当前故障数
        int failTotal = dataDao.failTotal();
        db.setFailTotal(failTotal);
        //今日报警数
        int alamTody = dataDao.alamTody(time);
        db.setAlarmTody(alamTody);
        //今日故障数
        int failTody = dataDao.failTody(time);
        db.setFailTody(failTody);

        /*//两周内日期及报警统计
        //1.获取当天的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        //2.获取第前14天的日期*/


        //Date begin = sdf.parse(request.getParameter("mbegin"));
        //Date end = sdf.parse(request.getParameter("mend"));
        //List<Date> lDate = findDates(begin, end);

        /*int counts[] = {0,1,0,0,0,2,0,0,0,3,0,0,0,1};
        db.setTwoWeeksReportCounts(counts);*/

        return db;
    }

    /**
     * 获取一段时间内每天的时间
     * @param
     * @param
     * @return
     */
    public List<Date> findDates(String begin, String end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(begin);// 将字符串转化为时间格式
        Date dEnd = sdf.parse(end);// 将字符串转化为时间格式
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取前几天的时间
     * @param farmat 返回日期格式
     * @param date 传入的初始日期
     * @param num 想要得到的哪天（正数：代表后num天，负数：代表前num天）
     * @return
     * @throws ParseException
     */
    public String getAnotherDay(String farmat, String date, Integer num) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(farmat);
        Date date_ = sdf.parse(date);// 将字符串转化为时间格式
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date_);// 把开始日期赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, num); // 设置为num天
        //calendar.add(Calendar.HOUR_OF_DAY, num); // 设置为num小时
        //calendar.add(Calendar.MONTH, num); // 设置为num月
        Date resultDate = calendar.getTime(); // 得到时间
        return sdf.format(resultDate);
    }

}
