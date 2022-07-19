package edu.dto.req;

import lombok.Data;

/**
 * @author 执笔
 * @date 17:20
 */
@Data
public class UserPwdReq {

    /**
     * 老密码
     */
    private String password;

    /**
     * 新密码
     */
    private String pwd;

    /**
     * 新密码
     */
    private String pwd2;


}
