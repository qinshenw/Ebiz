package edu.mapper;

import cn.hutool.crypto.digest.MD5;
import edu.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 执笔
 * @date  21:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("admin");
        user.setName("超级管理员");
        user.setRole("admin");
        user.setPassword(MD5.create().digestHex("123456"));
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }
}