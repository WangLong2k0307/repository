package cn.pslab.wanglong.dao;

import cn.pslab.wanglong.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
