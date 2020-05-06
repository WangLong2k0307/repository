package cn.pslab.dao;

import cn.pslab.domain.Role;

import java.util.List;

public interface RoleDao {
    //查询总记录数
    public int findTotalCount(String rname);

    //根据start,PageSize查询当前页数据集合
    public List<Role> findByPage(int start,int pageSize,String rname);

    //根据角色名查询对象
    public Role findByRname(String rname);

    //保存角色信息
    public void save(Role role);

    public Role findOne(int rid);

    public void updateRname(int rid,String rname);

    public void updateRemark(int rid,String remark);

    public void updateRli(int rid,int rli);

    public void updateAeq(int rid,int aeq);

    public void updateUeq(int rid,int ueq);

    public void updateDeq(int rid,int deq);

    public void updateAsubaccount(int rid,int asub_account);

    public void updateUsubaccount(int rid,int usub_account);

    public void updateDsubaccount(int rid,int dsub_account);

}
