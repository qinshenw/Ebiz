package edu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.support.base.BaseDomain;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 *
 * @author 执笔
 * @date 20:23:07
 */

@Data
public class User extends BaseDomain implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型
     * 普通用户
     * admin  管理员
     */
    private String role;

    /**
     *
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;
}