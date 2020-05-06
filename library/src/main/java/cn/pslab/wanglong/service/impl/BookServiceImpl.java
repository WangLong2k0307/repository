package cn.pslab.wanglong.service.impl;

import cn.pslab.wanglong.dao.BookDao;
import cn.pslab.wanglong.dao.BookImgDao;
import cn.pslab.wanglong.dao.FavoriteDao;
import cn.pslab.wanglong.dao.SellerDao;
import cn.pslab.wanglong.dao.impl.BookDaoImpl;
import cn.pslab.wanglong.dao.impl.BookImgDaoImpl;
import cn.pslab.wanglong.dao.impl.FavoriteDaoImpl;
import cn.pslab.wanglong.dao.impl.SellerDaoImpl;
import cn.pslab.wanglong.domain.*;
import cn.pslab.wanglong.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    private BookImgDao bookImgDao = new BookImgDaoImpl();

    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Book> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Book> pb = new PageBean<Book>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页数据数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = bookDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Book> list = bookDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }


    @Override
    public Book findOne(String rid) {

        //根据rid去book表中查询book对象
        Book book = bookDao.findOne(Integer.parseInt(rid));

        //根据book的rid查询集合信息
        List<BookImg> bookImgList = bookImgDao.findByRid(book.getRid());

        //将集合设置到book对象
        book.setBookImgList(bookImgList);

        //根据book的sid查询出版社(seller)对象
        Seller seller = sellerDao.findById(book.getSid());
        book.setSeller(seller);

        //查询收藏数量
        int count = favoriteDao.findCountById(book.getRid());
        book.setCount(count);
        return book;
    }
}
