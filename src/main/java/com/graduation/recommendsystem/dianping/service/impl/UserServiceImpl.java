package com.graduation.recommendsystem.dianping.service.impl;

import com.graduation.recommendsystem.dianping.dao.UserModelMapper;
import com.graduation.recommendsystem.dianping.model.UserModel;
import com.graduation.recommendsystem.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
