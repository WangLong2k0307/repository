package cn.pslab.service;

import cn.pslab.domain.DataBean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface DataService {

    /**
     * 查找统计数据
     * @return
     */
    public DataBean findData();

    public List<Date> findDates(String dBegin, String dEnd) throws ParseException;

    public String getAnotherDay(String farmat, String date, Integer num) throws ParseException;
}
