package edu.domain;

import edu.support.base.BaseDomain;
import lombok.Data;

/**
 * 账单
 * @author 执笔
 * @date 2021/5/8  17:47
 */
@Data
public class Bill extends BaseDomain {

    /**
      *
      */
    private Long userId;

    /**
     * 收费周期
     */
    private String date;


    private Double total;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 状态
     *
     * 待支付
     * 已支付
     * 催缴
     */
    private String status;


}
