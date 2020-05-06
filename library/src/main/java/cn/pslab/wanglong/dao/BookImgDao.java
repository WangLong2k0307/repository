package cn.pslab.wanglong.dao;

import cn.pslab.wanglong.domain.BookImg;

import java.util.List;

public interface BookImgDao {

    /**
     * 根据book的rid查询图片
     * @param rid
     * @return
     */
    public List<BookImg> findByRid(int rid);
}
