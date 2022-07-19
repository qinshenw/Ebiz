package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.OrdersDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseService;
import edu.domain.Orders;
import org.springframework.stereotype.Service;


/**
 * (Orders)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-07 23:48:10
 */
public interface OrdersService extends BaseService<Orders> {

    PageInfo<OrdersDto> pageDto(BasePage rowPage, QueryWrapper<Orders> wrapper);
}
