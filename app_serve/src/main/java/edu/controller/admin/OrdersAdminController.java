package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.Orders;
import edu.dto.OrdersDto;
import edu.service.OrdersService;
import edu.support.base.BasePage;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Orders)表控制层
 *
 * @author 执笔
 * @date 2021-05-07 23:48:10
 */
@RestController
@RequestMapping("/api/admin/orders")
public class OrdersAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private OrdersService ordersService;


    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(OrdersDto orders, BasePage rowPage) {
        QueryWrapper<Orders> wrapper = new MybatisWrapper<Orders>()
                .like("u.username", orders.getUsername())
                .like("u.name", orders.getNickname())
                .orderByDesc("create_time");
        if ("user".equals(loginUser().getRole())) {
            wrapper.eq("s.user_id", loginUserId());
        }
        PageInfo<OrdersDto> pageInfo = ordersService.pageDto(rowPage, wrapper);
        return JsonResponse.success(pageInfo);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public JsonResponse detail(@PathVariable Long id) {
        Orders orders = ordersService.getById(id);
        if (null == orders) {
            orders = new Orders();
        }
        return JsonResponse.success(orders);
    }

    /**
     * 更新
     *
     * @param orders
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(Orders orders) {
        if(orders.getId() == null){
            orders.setUserId(loginUserId());
            orders.setStatus("待处理");
        }

        ordersService.saveOrUpdate(orders);
        return JsonResponse.success();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        ordersService.removeById(id);
        return JsonResponse.success();
    }

    /**
     * 详情
     *
     * @return
     */
    @GetMapping("my")
    public JsonResponse my() {
        Orders orders = ordersService.getOne("user_id", loginUserId());
        if (null == orders) {
            orders = new Orders();
        }
        return JsonResponse.success(orders);
    }
}
    


