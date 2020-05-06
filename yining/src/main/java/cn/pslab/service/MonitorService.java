package cn.pslab.service;

import cn.pslab.domain.Current;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Voltage;

import java.util.List;

/**
 * 设备监控的业务接口
 */
public interface MonitorService {

    /**
     * 查询所有设备信息
     * @return
     */
    public List<Equipment> findEquipment();

    //查询电流
    public List<Current> findCurrent(int id);

    //查询电压
    public List<Voltage> findVoltage(int id);
}
