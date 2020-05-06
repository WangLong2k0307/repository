package cn.pslab.service.impl;

import cn.pslab.dao.EquipDao;
import cn.pslab.dao.impl.EquipDaoImpl;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.PageBean;
import cn.pslab.domain.Role;
import cn.pslab.domain.User;
import cn.pslab.service.EquipListService;


import java.util.List;

public class EquipListServiceImpl implements EquipListService {
    private EquipDao dao = new EquipDaoImpl();

    /**
     * 查询equipment表中所有的记录
     * @param currentPage
     * @param pageSize
     * @param name
     * @param infor
     * @param sta
     * @return
     */
    @Override
    public PageBean<Equipment> findAll(int currentPage, int pageSize, String name, String infor, String sta){
        //1、创建空的PageBean对象
        PageBean<Equipment> pb = new PageBean<Equipment>();
        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //3、调用dao查询总记录数
        int totalCount = dao.findTotalCount(name);

        pb.setTotalCount(totalCount);

        //4、调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * pageSize;
        List<Equipment> list = dao.pageQuery(start, pageSize, name, infor, sta);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % pageSize) == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;

    }

    /**
     * 查询角色表的所有信息
     * @param
     * @return
     */
    @Override
    public List<Role> findRole() {
        List<Role> list = dao.findRole();
        return list;
    }

    @Override
    public List<User> findUser() {
        List<User> userList = dao.findUser();
        return userList;
    }

    @Override
    public Equipment findOne(String eq_id) {
        return dao.findOne(eq_id);
    }

    @Override
    public boolean equipAdd(Equipment equipment) {
        Equipment equip = dao.findEquip(equipment.getName());
        if (equip != null){
            return false;
        }
        dao.equipAdd(equipment);
        return true;
    }

    @Override
    public void update(String name, String value, String eq_id) {
        dao.equipUpdate(name, value, eq_id);
    }

    @Override
    public List<Equipment> query() {
        return dao.query();
    }


}
