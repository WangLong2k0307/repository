package cn.pslab.dao.impl;

import cn.pslab.dao.RoleDao;
import cn.pslab.domain.Role;
import cn.pslab.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findTotalCount(String rname) {
        //1.定义sql模板
        String sql="select count(*) from role where 1 = 1 ";
        StringBuilder sb=new StringBuilder(sql);
        //条件列表
        List params=new ArrayList();
        //2.判断参数是否有值
        if (rname!=null && rname.length()>0){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql=sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    /**
     * 根据start,PageSize查询当前页数据集合
     * @return
     */
    @Override
    public List<Role> findByPage(int start, int pageSize,String rname) {
        //String sql="select * from pro_table where cid=? limit ? , ?";
        String sql=" select * from role where 1 = 1";
        //1.定义sql模板
        StringBuilder sb=new StringBuilder(sql);
        //条件列表
        List params=new ArrayList();
        //2.判断参数是否有值
        if (rname!=null && rname.length()>0){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");//添加对应的值
        }
        sb.append(" limit ? , ? ");//分页条件
        sql=sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Role>(Role.class),params.toArray());
    }


    public Role findByRname(String rname){
        Role role=null;
        try{
            //定义sql
            String sql="select * from role where rname=?";
            //执行sql
            role = template.queryForObject(sql, new BeanPropertyRowMapper<Role>(Role.class),rname);
        }catch (Exception e){

        }

        return role;
    }

    @Override
    public void save(Role role) {
        //1.定义sql
        String sql="insert into role(rname,remark,rli,aeq,ueq,deq,asub_account,usub_account,dsub_account) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql,role.getRname(),
                role.getRemark(),
                role.getRli(),
                role.getAeq(),
                role.getUeq(),
                role.getDeq(),
                role.getAsub_account(),
                role.getUsub_account(),
                role.getDsub_account());
    }

    @Override
    public Role findOne(int rid) {
        String sql="select * from role where rid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Role>(Role.class),rid);
    }

    @Override
    public void updateRname(int rid,String rname) {
        String sql="update role set rname=? where rid=?";
        template.update(sql,rname,rid);
    }

    @Override
    public void updateRemark(int rid, String remark) {
        String sql="update role set remark=? where rid=?";
        template.update(sql,remark,rid);
    }

    @Override
    public void updateRli(int rid, int rli) {
        String sql="update role set rli=? where rid=?";
        template.update(sql,rli,rid);
    }

    @Override
    public void updateAeq(int rid, int aeq) {
        String sql="update role set aeq=? where rid=?";
        template.update(sql,aeq,rid);
    }

    @Override
    public void updateUeq(int rid, int ueq) {
        String sql="update role set ueq=? where rid=?";
        template.update(sql,ueq,rid);
    }

    @Override
    public void updateDeq(int rid, int deq) {
        String sql="update role set deq=? where rid=?";
        template.update(sql,deq,rid);
    }

    @Override
    public void updateAsubaccount(int rid, int asub_account) {
        String sql="update role set asub_account=? where rid=?";
        template.update(sql,asub_account,rid);
    }

    @Override
    public void updateUsubaccount(int rid, int usub_account) {
        String sql="update role set usub_account=? where rid=?";
        template.update(sql,usub_account,rid);
    }

    @Override
    public void updateDsubaccount(int rid, int dsub_account) {
        String sql="update role set dsub_account=? where rid=?";
        template.update(sql,dsub_account,rid);
    }

}
