package edu.support.service;

import cn.hutool.crypto.digest.MD5;
import org.springframework.stereotype.Service;

/**
 * @author 执笔
 * @date 10:53
 */
@Service
public class PasswordEncoder {

    /**
     * 校验密码
     *
     * @param oldPassword
     * @param password
     * @return
     */
    public boolean matches(String oldPassword, String password) {
        return password.equalsIgnoreCase(encode(oldPassword));
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public String encode(String password) {
        return MD5.create().digestHex(password);
    }
}
