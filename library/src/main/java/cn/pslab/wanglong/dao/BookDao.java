package cn.pslab.wanglong.dao;

import cn.pslab.wanglong.domain.Book;

import java.util.List;

public interface BookDao {

    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid、start，pageSize查询当前页的数据集合
     */
    public List<Book> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Book findOne(int rid);
}
