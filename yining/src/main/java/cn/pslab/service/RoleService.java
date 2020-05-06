package cn.pslab.service;

import cn.pslab.domain.PageBean;
import cn.pslab.domain.Role;

public interface RoleService {
    public PageBean<Role> pageQuery(int currentPage,int pageSize,String rname);
    /**
     * 角色添加
     * @param role
     * @return
     */
    boolean add(Role role);

    //根据id查询
    Role findOne(String rid);

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
