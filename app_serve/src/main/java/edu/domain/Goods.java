package edu.domain;

import edu.support.base.BaseDomain;
import lombok.Data;

/**
 * 
 * @author 执笔
 * @date 2021/5/8  17:03
 */
@Data
public class Goods extends BaseDomain {


    /**
     * 楼栋号
     */
    private String no;
    
    /**
     * 房间号
     */
    private String room;

    /**
     * 房间面积
     */
    private Double size;

    /**
      *
      */
    private Long userId;
}
