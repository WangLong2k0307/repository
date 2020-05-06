package cn.pslab.service;

import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.PageBean;

import java.util.List;

public interface FireLogService {
    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param infor
     * @param name
     * @return
     */
    PageBean<Alarm> pageQuery(int currentPage, int pageSize, String name, String infor, String sta);

    /**
     * 查询数据
     * @return
     */
    List<Alarm> query();
}
