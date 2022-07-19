package edu.domain;

import edu.support.base.BaseDomain;
import lombok.Data;

/**
 * 信息
 * @author 执笔
 * @date 2021/5/5 8:50
 */
@Data
public class Notice extends BaseDomain {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     *
     */
    private String type;
}
