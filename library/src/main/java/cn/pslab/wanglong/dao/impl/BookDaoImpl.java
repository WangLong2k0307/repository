package cn.pslab.wanglong.dao.impl;

import cn.pslab.wanglong.dao.BookDao;
import cn.pslab.wanglong.domain.Book;
import cn.pslab.wanglong.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rname) {
        //String sql = "select count(*) from tab_book where cid = ? ";
        //定义sql模板
        String sql = "select count(*) from tab_book where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //params放置条件
        List params = new ArrayList();
        //判断参数是否有值
        if (cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Book> findByPage(int cid, int start, int pageSize, String rname) {


        String sql = "select * from tab_book where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //params放置条件
        List params = new ArrayList();
        //判断参数是否有值
        if (cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        //分页条件查询
        sb.append(" limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Book>(Book.class), params.toArray());
    }

    @Override
    public Book findOne(int rid) {

        String sql = "select * from tab_book where rid = ?";

        return template.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), rid);
    }
}
