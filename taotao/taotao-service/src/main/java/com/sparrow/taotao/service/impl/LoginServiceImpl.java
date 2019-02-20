package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.User;
import com.sparrow.taotao.dao.UserMapper;
import com.sparrow.taotao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuefeiyuluo on 2019/2/20.
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer queryByUserName(String userName) {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }


    @Override
    public Integer addUserInfo(User user) {
        return userMapper.insert(user);
    }
}
