package cn.pslab.service.impl;

import cn.pslab.dao.FireLogDao;
import cn.pslab.dao.impl.FireLogDaoImpl;
import cn.pslab.domain.Alarm;
import cn.pslab.domain.Equipment;
import cn.pslab.domain.PageBean;
import cn.pslab.service.FireLogService;

import java.util.List;

public class FireLogServiceImpl implements FireLogService {
    private FireLogDao fireLogDao = new FireLogDaoImpl();
    @Override
    public PageBean<Alarm> pageQuery(int currentPage, int pageSize, String name, String infor, String sta) {
        //1、创建空的PageBean对象
        PageBean<Alarm> pb = new PageBean<Alarm>();
        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //pb.setName(name);
        //3、调用dao查询总记录数
        int totalCount = fireLogDao.findTotalCount(name);

        pb.setTotalCount(totalCount);

        //4、调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * pageSize;
        List<Alarm> list = fireLogDao.pageQuery(start, pageSize, name, infor, sta);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % pageSize) == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }



    @Override
    public List<Alarm> query() {

        return fireLogDao.query();
    }
}
