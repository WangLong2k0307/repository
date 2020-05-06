package cn.pslab.wanglong.dao.impl;

import cn.pslab.wanglong.dao.BookImgDao;
import cn.pslab.wanglong.domain.BookImg;
import cn.pslab.wanglong.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookImgDaoImpl implements BookImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<BookImg> findByRid(int rid) {

        String sql = "select * from tab_book_img where rid = ?";

        return template.query(sql, new BeanPropertyRowMapper<BookImg>(BookImg.class), rid);
    }
}
