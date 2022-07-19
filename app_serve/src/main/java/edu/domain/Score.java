package edu.domain;

import edu.support.base.BaseDomain;
import lombok.Data;

/**
 * 水电气表信息
 *
 * @author 执笔
 * @date 17:42
 */
@Data
public class Score extends BaseDomain {

    /**
     *
     */
    private Long userId;

    /**
     * 水
     */
    private Double water;

    private Double electricity;

    /**
     * 气
     */
    private Double gas;

    /**
     *
     */
    private String date;


}
