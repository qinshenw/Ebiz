package edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.OrdersDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseServiceImpl;
import edu.mapper.OrdersMapper;
import edu.domain.Orders;
import edu.service.OrdersService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Orders)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-07 23:48:06
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PageInfo<OrdersDto> pageDto(BasePage rowPage, QueryWrapper<Orders> wrapper) {
        startPage(rowPage);
        return new PageInfo<>(ordersMapper.selectDto(wrapper));
    }
}
