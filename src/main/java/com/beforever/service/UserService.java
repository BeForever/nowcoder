package com.beforever.service;

import com.beforever.dao.UserDao;
import com.beforever.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BeForever on 17/5/17.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    public User getUser(int id) {
        return userDao.selectById(id);
    }
}