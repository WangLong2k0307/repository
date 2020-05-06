package cn.pslab.wanglong.service;

import cn.pslab.wanglong.domain.Book;
import cn.pslab.wanglong.domain.PageBean;

public interface BookService {
    PageBean<Book> pageQuery(int cid, int currentPage, int pageSize, String rname);
    /**
     * 根据rid查询
     * @param rid
     * @return
     */
    Book findOne(String rid);
}
