package cn.pslab.dao.impl;

import cn.pslab.dao.MonitorDao;
import cn.pslab.domain.Current;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Voltage;
import cn.pslab.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MonitorDaoImpl implements MonitorDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找设备信息
     * @return
     */
    @Override
    public List<Equipment> findEquipment() {
        //使用jdbc操作数据库
        //1.定义sql
        String sql = "select * from equipment";
        return template.query(sql,new BeanPropertyRowMapper<Equipment>(Equipment.class));
    }

    @Override
    public List<Current> findCurrent(int id) {
        String sql = "select * from current where c_id = ?";
        return template.query(sql,new BeanPropertyRowMapper<Current>(Current.class),id);
    }

    @Override
    public List<Voltage> findVoltage(int id) {
        String sql = "select * from voltage where v_id = ?";
        return template.query(sql,new BeanPropertyRowMapper<Voltage>(Voltage.class),id);
    }

    @Override
    public void setEqTime() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
        String today = df.format(new Date());// new Date()为获取当前系统时间
        String sql = "update equipment set datetime = ? where 1 = 1";
        int count = template.update(sql, today);
        System.out.println(count);
    }


}
