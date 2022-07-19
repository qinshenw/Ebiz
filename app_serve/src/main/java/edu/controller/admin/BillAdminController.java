package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.Bill;
import edu.domain.Goods;
import edu.dto.BillDto;
import edu.service.BillService;
import edu.service.GoodsService;
import edu.support.base.BasePage;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (Bill)表控制层
 *
 * @author 执笔
 * @date 2021-05-08 17:53:44
 */
@RestController
@RequestMapping("/api/admin/bill")
public class BillAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private BillService billService;
    @Autowired
    private GoodsService goodsService;


    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(BillDto bill, BasePage rowPage) {

        // 每个小时自动生成账单
        List<Goods> list = goodsService.list();
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH");
        for (Goods goods : list) {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try {
                int count = billService.count(new QueryWrapper<Bill>().eq("date", date).eq("user_id", goods.getUserId()));
                if (count == 0) {
                    Bill item = new Bill();
                    item.setDate(date);
                    item.setUserId(goods.getUserId());
                    item.setUnitPrice(2.82);
                    item.setStatus("待支付");
                    item.setTotal(item.getUnitPrice() * goods.getSize());
                    billService.save(item);
                }
            } finally {
                lock.unlock();
            }
        }


        QueryWrapper<Bill> wrapper = new MybatisWrapper<Bill>()
                .like("u.username", bill.getUsername())
                .like("u.name", bill.getNickname())
                .like("g.no", bill.getGoodsNo())
                .like("g.room", bill.getGoodsRoom())
                .orderByDesc("create_time");

        if ("user".equals(loginUser().getRole())) {
            wrapper.eq("s.user_id", loginUserId());
        }

        PageInfo<BillDto> pageInfo = billService.pageDto(rowPage, wrapper);
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
        Bill bill = billService.getById(id);
        if (null == bill) {
            bill = new Bill();
        }
        return JsonResponse.success(bill);
    }

    /**
     * 更新
     *
     * @param bill
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(Bill bill) {
        billService.saveOrUpdate(bill);
        return JsonResponse.success();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        billService.removeById(id);
        return JsonResponse.success();
    }
}
    


