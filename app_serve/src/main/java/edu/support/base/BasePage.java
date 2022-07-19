package edu.support.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页的基础
 *
 * @author 执笔
 * @date  10:09
 */
@Getter
@Setter
public class BasePage implements Serializable {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
