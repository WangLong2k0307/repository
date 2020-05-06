package cn.pslab.wanglong.service;

import cn.pslab.wanglong.domain.User;

public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
