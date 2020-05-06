package cn.pslab.service.impl;

import cn.pslab.dao.RoleDao;
import cn.pslab.dao.impl.RoleDaoImpl;
import cn.pslab.domain.PageBean;
import cn.pslab.domain.Role;
import cn.pslab.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao=new RoleDaoImpl();

    @Override
    public PageBean<Role> pageQuery(int currentPage, int pageSize, String rname) {
//封装PageBean
        PageBean<Role> pb = new PageBean();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount=roleDao.findTotalCount(rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start=(currentPage-1)*pageSize;//开始的记录数
        List<Role> list=roleDao.findByPage(start,pageSize,rname);
        pb.setList(list);

        //设置总页数=totalCount/pageSize
        int totalPage=totalCount % pageSize==0 ? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    /**
     * 用户注册
     * @param role
     * @return
     */
    @Override
    public boolean add(Role role) {
        //1.根据名查询用户对象
        Role r=roleDao.findByRname(role.getRname());
        //判断是否为空
        if(r!=null){
            //角色存在，添加失败
            return false;
        }
        //2.保存用户信息
        roleDao.save(role);
        return true;
    }

    @Override
    public Role findOne(String rid) {
        Role role=roleDao.findOne(Integer.parseInt(rid));
        return role;
    }

    @Override
    public void updateRname(int rid, String rname) {
        roleDao.updateRname(rid,rname);
    }

    @Override
    public void updateRemark(int rid, String remark) {
        roleDao.updateRemark(rid,remark);
    }

    @Override
    public void updateRli(int rid, int rli) {
        roleDao.updateRli(rid,rli);
    }

    @Override
    public void updateAeq(int rid, int aeq) {
        roleDao.updateAeq(rid,aeq);
    }

    @Override
    public void updateUeq(int rid, int ueq) {
        roleDao.updateUeq(rid,ueq);
    }

    @Override
    public void updateDeq(int rid, int deq) {
        roleDao.updateDeq(rid,deq);
    }

    @Override
    public void updateAsubaccount(int rid, int asub_account) {
        roleDao.updateAsubaccount(rid,asub_account);
    }

    @Override
    public void updateUsubaccount(int rid, int usub_account) {
        roleDao.updateUsubaccount(rid,usub_account);
    }

    @Override
    public void updateDsubaccount(int rid, int dsub_account) {
        roleDao.updateDsubaccount(rid,dsub_account);
    }


}
