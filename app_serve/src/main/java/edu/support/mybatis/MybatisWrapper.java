package edu.support.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * mybatis 查询器
 *
 * @author 执笔
 * @date  18:12
 */
public class MybatisWrapper<T> extends QueryWrapper<T> {

    private boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return StringUtils.isNotBlank(obj.toString());
        }
        return true;
    }

    @Override
    public MybatisWrapper<T> eq(String column, Object val) {
        super.eq(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> ne(String column, Object val) {
        super.ne(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> gt(String column, Object val) {
        super.gt(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> ge(String column, Object val) {
        super.ge(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> lt(String column, Object val) {
        super.lt(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> le(String column, Object val) {
        super.le(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> like(String column, Object val) {
        super.like(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> likeLeft(String column, Object val) {
        super.likeLeft(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

    @Override
    public MybatisWrapper<T> likeRight(String column, Object val) {
        super.likeRight(StringUtils.isNotBlank(column) && isNullOrEmpty(val), column, val);
        return this;
    }

}
