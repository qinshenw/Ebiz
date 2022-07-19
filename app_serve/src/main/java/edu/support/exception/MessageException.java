package edu.support.exception;

/**
 * 自定义异常
 * @author 执笔
 * @date  19:26
 */
public class MessageException extends RuntimeException {

    public MessageException(String msg) {
        super(msg);
    }

}
