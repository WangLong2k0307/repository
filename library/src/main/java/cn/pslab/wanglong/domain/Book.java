package cn.pslab.wanglong.domain;

import java.util.List;

public class Book {
    private int rid;
    private String rname;
    private Double evaluate;
    private String bookIntroduce;
    private int sid;
    private String rdate;
    private int cid;
    private String rimage;
    private int count;
    private Category category;//所属分类
    private Seller seller;
    private List<BookImg> bookImgList;//商品详情图片列表

    /**
     * 无参构造函数
     */
    public Book(){

    }

    public Book(int rid, String rname, Double evaluate, String bookIntroduce, int sid, String rdate, int cid, String rimage, int count) {
        this.rid = rid;
        this.rname = rname;
        this.evaluate = evaluate;
        this.bookIntroduce = bookIntroduce;
        this.sid = sid;
        this.rdate = rdate;
        this.cid = cid;
        this.rimage = rimage;
        this.count = count;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BookImg> getBookImgList() {
        return bookImgList;
    }

    public void setBookImgList(List<BookImg> bookImgList) {
        this.bookImgList = bookImgList;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Double evaluate) {
        this.evaluate = evaluate;
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
