package cn.pslab.wanglong.service.impl;

import cn.pslab.wanglong.dao.FavoriteDao;
import cn.pslab.wanglong.dao.impl.FavoriteDaoImpl;
import cn.pslab.wanglong.domain.Favorite;
import cn.pslab.wanglong.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);


        return favorite != null;//对象有值返回true，对象为null返回false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}
