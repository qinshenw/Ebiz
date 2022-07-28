package edu.support.base.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import edu.support.base.BaseDomain;
import edu.support.base.BasePage;

import java.util.List;


/**
 * base service
 *
 * @param <T>
 * @author wqs
 */
public interface BaseService<T extends BaseDomain> extends IService<T> {


    /**
     * conditional paging
     *
     * @param rowPage
     * @param wrapper
     * @return
     */
    PageInfo<T> page(BasePage rowPage, Wrapper<T> wrapper);


    /**
     * query column
     * @param column
     * @param val
     * @return
     */
    List<T> list(String column, Object val);


    /**
     * query amount
     * @param column
     * @param val
     * @return
     */
    Integer count(String column, Object val);


    /**
     * query one
     * @param column
     * @param val
     * @return
     */
    T getOne(String column, Object val);

}
