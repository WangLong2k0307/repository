package cn.pslab.dao;

import cn.pslab.domain.Alarm;

import java.util.List;

public interface DataDao {

    //查找设备数量
    public int findEqCount();
    //查找单位数量
    public int findDepCount();
    //查找今日报警设备数量
    public int findAlarm(String time);
    //查找今日故障设备数量
    public int findFail(String time);


    //当前报警数
    public int alarmTotal();
    //当前故障数
    public int failTotal();
    //今日报警数
    public int alamTody(String time);
    //今日故障数
    public int failTody(String time);

    //两周内当天报警数
    public int findWeekCounts(String date1,String date2);

    //当天报警故障信息
    public List<Alarm> findTipToday(String time);

}
