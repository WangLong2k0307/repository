package cn.pslab.dao.impl;

import cn.pslab.dao.FireLogDao;
import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.util.JDBCUtils;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FireLogDaoImpl implements FireLogDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Alarm> query(){
        String sql = "select *,count(*) as count from alarm";
        List<Alarm> alarms = template.query(sql, new BeanPropertyRowMapper<Alarm>(Alarm.class));
        return alarms;
    }


    @Override
    public int findTotalCount(String name) {
        //定义sql语句
        String sql = "select count(*) from alarm where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (name != null && name.length() > 0){
            sb.append(" and eq_name like ? ");
            params.add("%"+name+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Alarm> pageQuery(int start, int pageSize, String name, String infor, String sta) {
        String sql = "select * from alarm where 1= 1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (name != null && name.length() > 0){
            sb.append(" and eq_name like ? ");
            params.add("%"+name+"%");
        }
        sb.append(" limit ?, ? ");
        params.add(start);
        params.add(pageSize);
        if (infor != null && infor.length() > 0){
            sb.append(" order by ? ");
            params.add(infor);
        }
        if (sta != null && sta.equalsIgnoreCase("desc")){
            sb.append("desc");
            params.add(sta);
        }
        sql = sb.toString();

        return template.query(sql, new BeanPropertyRowMapper<Alarm>(Alarm.class), params.toArray());
    }
}
