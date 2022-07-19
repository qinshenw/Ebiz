package edu.support.base.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.support.base.BaseDomain;
import edu.support.base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 基础 service 实现
 *
 * @param <T>
 * @author 执笔
 */
public class BaseServiceImpl<E extends BaseMapper<T>, T extends BaseDomain> extends ServiceImpl<E, T> implements BaseService<T> {

    @Autowired
    protected E mapper;


    @Override
    public PageInfo<T> page(BasePage rowPage, Wrapper<T> wrapper) {
        startPage(rowPage);
        return new PageInfo<>(mapper.selectList(wrapper));
    }


    @Override
    public List<T> list(String column, Object val) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>().eq(column, val)
                .orderByDesc("create_time");
        return list(queryWrapper);
    }

    @Override
    public Integer count(String column, Object val) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>().eq(column, val);
        return count(queryWrapper);
    }

    @Override
    public T getOne(String column, Object val) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>().eq(column, val);
        return getOne(queryWrapper);
    }

    /**
     * 开始分页
     *
     * @param page
     */
    protected void startPage(BasePage page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
    }

}
