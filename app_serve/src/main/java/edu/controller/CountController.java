package edu.controller;

import edu.domain.Bill;
import edu.domain.Orders;
import edu.service.BillService;
import edu.service.NoticeService;
import edu.service.OrdersService;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 执笔
 * @date 2021/5/9 10:50
 */
@RestController
@RequestMapping("api/count")
public class CountController extends BaseController {

    @Autowired
    private BillService billService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("index")
    public JsonResponse index(Model model) {
        Map<String, Object> data = new HashMap();
        // 未处理的报修
        // 未处理的投诉
        // 催缴中的账单
        // 未支付的账单
        MybatisWrapper<Orders> eq = new MybatisWrapper<Orders>().eq("type", "报修").eq("status", "待处理");
        if ("user".equals(loginUser().getRole())) {
            eq.eq("user_id", loginUserId());
        }
        Integer count = ordersService.count(eq);

        MybatisWrapper<Orders> eq1 = new MybatisWrapper<Orders>().eq("type", "投诉").eq("status", "待处理");
        if ("user".equals(loginUser().getRole())) {
            eq1.eq("user_id", loginUserId());
        }
        Integer count1 = ordersService.count(eq1);

        MybatisWrapper<Bill> eq2 = new MybatisWrapper<Bill>().eq("status", "未支付");
        if ("user".equals(loginUser().getRole())) {
            eq2.eq("user_id", loginUserId());
        }
        Integer count2 = billService.count(eq2);

        MybatisWrapper<Bill> eq3 = new MybatisWrapper<Bill>().eq("status", "已催缴");
        if ("user".equals(loginUser().getRole())) {
            eq3.eq("user_id", loginUserId());
        }
        Integer count3 = billService.count(eq3);

        data.put("count3", count3);
        data.put("count2", count2);
        data.put("count1", count1);
        data.put("count", count);

        return JsonResponse.success(data);
    }
}
