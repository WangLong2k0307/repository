package cn.pslab.dao;

import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;

import java.util.List;

public interface FireLogDao {
    /**
     * 查询总记录数
     * @return
     * @param name
     */
    int findTotalCount(String name);

    /**
     * 分页查询每页记录
     * @param start
     * @param pageSize
     * @param name
     * @return
     */
    List<Alarm> pageQuery(int start, int pageSize, String name, String infor, String sta);

    /**
     * 查询所有报警记录
     * @return
     */
    List<Alarm> query();
}
