package edu.support.base.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import edu.support.base.BaseDomain;
import edu.support.base.BasePage;

import java.util.List;


/**
 * 基础 service
 *
 * @param <T>
 * @author 执笔
 */
public interface BaseService<T extends BaseDomain> extends IService<T> {


    /**
     * 根据条件分页
     *
     * @param rowPage
     * @param wrapper
     * @return
     */
    PageInfo<T> page(BasePage rowPage, Wrapper<T> wrapper);


    /**
     * 根据某一列查询全部
     * @param column
     * @param val
     * @return
     */
    List<T> list(String column, Object val);


    /**
     * 查询数量
     * @param column
     * @param val
     * @return
     */
    Integer count(String column, Object val);


    /**
     * 查询一个
     * @param column
     * @param val
     * @return
     */
    T getOne(String column, Object val);

}
