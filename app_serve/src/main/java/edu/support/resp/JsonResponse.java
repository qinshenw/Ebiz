package edu.support.resp;


import lombok.Data;

import java.io.Serializable;

/**
 * @author wqs
 */
@Data
public class JsonResponse<T> implements Serializable {

    private String message;

    private String code;

    private T data;

    private Long timestamp;

    private JsonResponse(String message, String code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> JsonResponse<T> success(String info, T data) {
        return build("success", info, data);
    }


    public static <T> JsonResponse<T> success(String info) {
        return build("success", info, null);
    }


    public static <T> JsonResponse<T> success(T data) {
        return build("success", "success", data);
    }


    public static <T> JsonResponse<T> success() {
        return build("success", "success", null);
    }


    public static <T> JsonResponse<T> fail(String info) {
        return build("fail", info, null);
    }

    public static <T> JsonResponse<T> build(String code, String info, T data) {
        return new JsonResponse<>(info, code, data);
    }

    public static <T> JsonResponse<T> noLogin() {
        return build("noLogin", "请先登录", null);
    }

    public static <T> JsonResponse<T> noAuth() {
        return build("noAuth", "没有权限", null);
    }

}
