package com.sparrow.taotao.service;

import com.sparrow.taotao.User;

/**
 * Created by xuefeiyuluo on 2019/2/20.
 */
public interface LoginService {
    Integer queryByUserName(String userName);

    Integer addUserInfo(User user);

}
