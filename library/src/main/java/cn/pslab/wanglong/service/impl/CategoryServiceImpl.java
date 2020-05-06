package cn.pslab.wanglong.service.impl;

import cn.pslab.wanglong.dao.CategoryDao;
import cn.pslab.wanglong.dao.impl.CategoryDaoImpl;
import cn.pslab.wanglong.domain.Category;
import cn.pslab.wanglong.service.CategoryService;
import cn.pslab.wanglong.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1、从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3查询sorted中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2、判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
            //3、如果为空，需要从数据库查询，再将数据存入redis
            //从数据库查询
            cs = categoryDao.findAll();
            //将集合数据存储到redis中的category的key
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else {
            //4、如果不为空，将set数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
