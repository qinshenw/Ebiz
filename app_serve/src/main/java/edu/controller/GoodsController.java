package edu.controller;


import edu.domain.Goods;
import edu.service.GoodsService;
import edu.support.base.controller.BaseController;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Goods)表控制层
 *
 * @author 执笔
 * @date 2021-05-08 17:53:45
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private GoodsService goodsService;


    /**
     * 详情
     *
     * @return
     */
    @GetMapping("index")
    public JsonResponse index() {
        Goods goods = goodsService.getOne("user_id", loginUserId());
        if (null == goods) {
            goods = new Goods();
            goods.setUserId(loginUserId());
            goodsService.save(goods);
        }
        return JsonResponse.success(goods);
    }

    /**
     * 更新
     *
     * @param goods
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(Goods goods) {
        goodsService.saveOrUpdate(goods);
        return JsonResponse.success();
    }

}
    


