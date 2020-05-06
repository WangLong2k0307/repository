package cn.pslab.wanglong.dao.impl;

import cn.pslab.wanglong.dao.CategoryDao;
import cn.pslab.wanglong.domain.Category;
import cn.pslab.wanglong.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {

        String sql = "select * from tab_category ";

        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
