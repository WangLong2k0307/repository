package cn.pslab.service.impl;

import cn.pslab.dao.MonitorDao;
import cn.pslab.dao.impl.MonitorDaoImpl;
import cn.pslab.domain.Current;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Voltage;
import cn.pslab.service.MonitorService;

import java.util.List;

public class MonitorServiceImpl implements MonitorService {
    private MonitorDao monitorDao = new MonitorDaoImpl();

    @Override
    public List<Equipment> findEquipment() {
        //调用dao完成查询
        return monitorDao.findEquipment();
    }

    @Override
    public List<Current> findCurrent(int id) {
        return monitorDao.findCurrent(id);
    }

    @Override
    public List<Voltage> findVoltage(int id) {
        return monitorDao.findVoltage(id);
    }
}
