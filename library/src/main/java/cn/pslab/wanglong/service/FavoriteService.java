package cn.pslab.wanglong.service;

public interface FavoriteService {
    /**
     * 判断是否收藏
     * @param rid
     * @param cid
     * @return
     */
    public boolean isFavorite(String rid, int uid);

    void add(String rid, int uid);
}
