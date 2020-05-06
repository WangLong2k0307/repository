package cn.pslab.dao;

import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;

import java.util.List;

public interface EquipDao {
    /**
     * 分页时查找总记录数
     * @param name
     * @return
     */
    int findTotalCount(String name);

    /**
     * 分页查找
     * @param start
     * @param pageSize
     * @param name
     * @param infor
     * @param sta
     * @return
     */
    List<Equipment> pageQuery(int start, int pageSize, String name, String infor, String sta);

    /**
     * 查找角色
     * @return
     */
    List<Role> findRole();

    /**
     * 添加设备
     * @param equipment
     */
    void equipAdd(Equipment equipment);

    /**
     * 判断设备所属角色是否有权限
     * @param name
     * @return
     */
    Equipment findEquip(String name);

    /**
     * 修改设备信息
     * @param name
     * @param value
     * @param eq_id
     */
    void equipUpdate(String name, String value, String eq_id);

    /**
     * 修改设备时查找该设备的信息
     * @param eq_id
     * @return
     */
    Equipment findOne(String eq_id);

    /**
     * 查找用户
     * @return
     */
    List<User> findUser();

    /**
     * 查询所有报警记录
     * @return
     */
    List<Equipment> query();
}
