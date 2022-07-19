package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.Goods;
import edu.dto.GoodsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Goods)表数据库访问层
 *
 * @author 执笔
 * @date 2021-05-08 17:53:45
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsDto> selectDto(@Param("ew") QueryWrapper<Goods> wrapper);
}
