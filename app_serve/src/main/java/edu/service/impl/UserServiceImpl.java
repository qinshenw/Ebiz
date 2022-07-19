package edu.service.impl;


import edu.domain.User;
import edu.mapper.UserMapper;
import edu.service.UserService;
import edu.support.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author 执笔
 * @date 2021-03-29 17:40:23
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
}