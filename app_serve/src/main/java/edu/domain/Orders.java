package edu.domain;

import edu.support.base.BaseDomain;
import lombok.Data;

/**
 * 报修投诉
 * @author 执笔
 * @date 17:43
 */
@Data
public class Orders extends BaseDomain {

    /**
      * 
      */
    private Long userId;

    /**
     * 类型
     * 维修
     * 投诉
     */
    private String type;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    private String path;

    /**
     *
     */
    private String wxUser;

    /**
     * 结果
     *
     * 已改正、已上报
     */
    private String result;



    /**
     * 状态
     * 待处理
     * 已处理
     */
    private String status;

}
