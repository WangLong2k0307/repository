package cn.pslab.dao.impl;

import cn.pslab.dao.EquipDao;
import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;
import cn.pslab.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class EquipDaoImpl implements EquipDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有角色的信息
     * @param
     * @return
     */
    @Override
    public List<Role> findRole(){
        String sql = "select * from role";
        return template.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
    }

    /**
     * 添加设备
     * @param equipment
     */
    @Override
    public void equipAdd(Equipment equipment) {
        String sql = "insert into equipment (eq_id, name, equipment_class, use_department, address, producer, " +
                "type, sim_id, note, longitude, latitude, uid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, equipment.getEq_id(), equipment.getName(), equipment.getEquipment_class(), equipment.getUse_department(), equipment.getAddress(),
                equipment.getProducer(), equipment.getType(), equipment.getSim_id(), equipment.getNote(),
                equipment.getLongitude(), equipment.getLatitude(), equipment.getUid());

    }

    /**
     * 判断设备所属角色是否有权限
     * @param name
     * @return
     */
    @Override
    public Equipment findEquip(String name) {
        String sql = "select * from equipment where name = ?";
        Equipment equipment = null;
        try {
            equipment = template.queryForObject(sql, new BeanPropertyRowMapper<Equipment>(Equipment.class), name);
        }catch (Exception e){

        }
        return equipment;
    }

    @Override
    public void equipUpdate(String name, String value, String eq_id) {
        String sql = "update equipment set "+name+" = \'"+value+"\' where eq_id = ?";
        template.update(sql, eq_id);
    }

    @Override
    public Equipment findOne(String eq_id) {
        String sql = "select * from equipment where eq_id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Equipment>(Equipment.class), eq_id);
    }

    @Override
    public List<User> findUser() {
        String sql = "select * from user";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public List<Equipment> query() {
        String sql = "select * from equipment";
        List<Equipment> equipments = template.query(sql, new BeanPropertyRowMapper<Equipment>(Equipment.class));
        return equipments;
    }


    /**
     * 分页时查询总记录数
     * @param name
     * @return
     */
    @Override
    public int findTotalCount(String name) {
        String sql = "select count(*) from equipment where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (name != null && name.length() > 0){
            sb.append(" and name like ? ");
            params.add("%"+name+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 查询所有记录
     * @param start
     * @param pageSize
     * @param name
     * @param infor
     * @param sta
     * @return
     */
    @Override
    public List<Equipment> pageQuery(int start, int pageSize, String name, String infor, String sta) {
        String sql = "select * from equipment where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (name != null && name.length() > 0){
            sb.append(" and name like ? ");
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


        return template.query(sql, new BeanPropertyRowMapper<Equipment>(Equipment.class), params.toArray());
    }
}
