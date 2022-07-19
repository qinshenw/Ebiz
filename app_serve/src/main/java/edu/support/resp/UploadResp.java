package edu.support.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 上传文件响应
 * @author 执笔
 * @date  14:04
 */
@Data
@AllArgsConstructor
public class UploadResp {

    /**
     * 名称
     */
    private String name;

    /**
     *
     */
    private String url;

    /**
     * 标志
     */
    private String mark;
}
