package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.Orders;
import edu.dto.OrdersDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Orders)表数据库访问层
 *
 * @author 执笔
 * @date 2021-05-07 23:48:06
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrdersDto> selectDto(@Param("ew") QueryWrapper<Orders> wrapper);
}
