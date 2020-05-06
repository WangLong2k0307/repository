package cn.pslab.domain;

import java.util.Date;
import java.util.List;

public class DataBean {
    private int eqCount;//设备数量
    private int depCount;//单位数量
    private int alarmRate;//报警比例
    private int failRate;//故障比例
    private int alarmTotal;//当前报警数
    private int failTotal;//当前故障数
    private int alarmTody;//今日报警数
    private int failTody;//今日故障数

    private List<Date> twoWeeksDays;//两周内的日期
    private int[] twoWeeksReportCounts;//相应报警数

    public List<Date> getTwoWeeksDays() {
        return twoWeeksDays;
    }

    public void setTwoWeeksDays(List<Date> twoWeeksDays) {
        this.twoWeeksDays = twoWeeksDays;
    }

    public int[] getTwoWeeksReportCounts() {
        return twoWeeksReportCounts;
    }

    public void setTwoWeeksReportCounts(int[] twoWeeksReportCounts) {
        this.twoWeeksReportCounts = twoWeeksReportCounts;
    }

    /*private List<A> list;*/

    public int getEqCount() {
        return eqCount;
    }

    public void setEqCount(int eqCount) {
        this.eqCount = eqCount;
    }

    public int getDepCount() {
        return depCount;
    }

    public void setDepCount(int depCount) {
        this.depCount = depCount;
    }

    public int getAlarmRate() {
        return alarmRate;
    }

    public void setAlarmRate(int alarmRate) {
        this.alarmRate = alarmRate;
    }

    public int getFailRate() {
        return failRate;
    }

    public void setFailRate(int failRate) {
        this.failRate = failRate;
    }

    public int getAlarmTotal() {
        return alarmTotal;
    }

    public void setAlarmTotal(int alarmTotal) {
        this.alarmTotal = alarmTotal;
    }

    public int getFailTotal() {
        return failTotal;
    }

    public void setFailTotal(int failTotal) {
        this.failTotal = failTotal;
    }

    public int getAlarmTody() {
        return alarmTody;
    }

    public void setAlarmTody(int alarmTody) {
        this.alarmTody = alarmTody;
    }

    public int getFailTody() {
        return failTody;
    }

    public void setFailTody(int failTody) {
        this.failTody = failTody;
    }

}
