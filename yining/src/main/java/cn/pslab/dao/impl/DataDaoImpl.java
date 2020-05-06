package cn.pslab.dao.impl;

import cn.pslab.dao.DataDao;
import cn.pslab.domain.Alarm;
import cn.pslab.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DataDaoImpl implements DataDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findEqCount() {
        String sql = "select count(*) from equipment";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int findDepCount() {
        String sql = "select count(*) from (select use_department from equipment group by use_department) as a";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int findAlarm(String time) {
        String sql = "select count(*) from (select distinct eq_name from alarm where type = '报警' and alarmtime like '"+time+"%' ) as a";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int findFail(String time) {
        String sql = "select count(*) from (select distinct eq_name from alarm where type = '故障' and alarmtime like '"+time+"%' ) as a";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int alarmTotal() {
        String sql = "select count(*) from alarm where type = '报警' ";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int failTotal() {
        String sql = "select count(*) from alarm where type = '故障' ";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int alamTody(String time) {
        String sql = "select count(*) from alarm where type = '报警' and alarmtime like '"+time+"%'";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int failTody(String time) {
        String sql = "select count(*) from alarm where type = '故障' and alarmtime like '"+time+"%' ";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public int findWeekCounts(String date1,String date2) {
        String sql = "select count(*) from alarm where alarmtime >= ? and alarmtime < ? ";
        return template.queryForObject(sql,Integer.class,date1,date2);
    }

    @Override
    public List<Alarm> findTipToday(String time) {
        String sql = "select * from alarm where alarmtime like '"+time+"%' ";
        return template.query(sql,new BeanPropertyRowMapper<Alarm>(Alarm.class));
    }

    /*@Test
    public void test1(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(new Date());
        System.out.println(format);// new Date()为获取当前系统时间
    }
    @Test
    public void test2(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(new Date());
        String sql = "select count(*) from alarm where alarmtime like '"+format+"%' ";
        Integer integer = template.queryForObject(sql, Integer.class);
        System.out.println(integer);// new Date()为获取当前系统时间
    }*/
}
