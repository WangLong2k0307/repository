package cn.pslab.dao;

import cn.pslab.domain.Current;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Voltage;

import java.util.List;

public interface MonitorDao {

    public List<Equipment> findEquipment();

    public List<Current> findCurrent(int id);

    public List<Voltage> findVoltage(int id);

    public void setEqTime();


}
