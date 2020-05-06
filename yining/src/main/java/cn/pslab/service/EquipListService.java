package cn.pslab.service;

import cn.pslab.domain.*;

import java.util.List;

public interface EquipListService {
    /**
     * 分页查找数据
     * @param currentPage
     * @param pageSize
     * @param name
     * @param infor
     * @param sta
     * @return
     */
    PageBean<Equipment> findAll(int currentPage, int pageSize, String name, String infor, String sta);

    /**
     * 查找角色
     * @return
     */
    List<Role> findRole();

    /**
     * 查询用户
     * @return
     */
    List<User> findUser();
    /**
     * 修改时查找修改对象的信息
     * @param eq_id
     * @return
     */
    Equipment findOne(String eq_id);

    /**
     * 添加设备
     * @param equipment
     * @return
     */
    boolean equipAdd(Equipment equipment);

    /**
     * 修改设备
     * @param name
     * @param value
     * @param eq_id
     */
    void update(String name, String value, String eq_id);

    /**
     * 查询数据
     * @return
     */
    List<Equipment> query();
}
