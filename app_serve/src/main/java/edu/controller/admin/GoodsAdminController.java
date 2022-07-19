package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.Goods;
import edu.dto.GoodsDto;
import edu.service.GoodsService;
import edu.support.base.controller.BaseController;
import edu.support.base.BasePage;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Goods)表控制层
 *
 * @author 执笔
 * @date 2021-05-08 17:53:45
 */
@RestController
@RequestMapping("/api/admin/goods")
public class GoodsAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private GoodsService goodsService;


    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(GoodsDto goods, BasePage rowPage) {
        QueryWrapper<Goods> wrapper = new MybatisWrapper<Goods>()
                .ne("role", "admin")
                .like("u.username", goods.getUsername())
                .like("u.name", goods.getNickname())
                .like("g.no", goods.getNo())
                .like("g.room", goods.getRoom())
                .orderByDesc("create_time");

        PageInfo<GoodsDto> pageInfo = goodsService.pageDto(rowPage, wrapper);
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
        Goods goods = goodsService.getById(id);
        if (null == goods) {
            goods = new Goods();
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

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        goodsService.removeById(id);
        return JsonResponse.success();
    }
}
    


