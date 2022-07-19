package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.Score;
import edu.domain.User;
import edu.dto.ScoreDto;
import edu.service.ScoreService;
import edu.service.UserService;
import edu.support.base.BasePage;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Score)表控制层
 *
 * @author 执笔
 * @date 2021-05-07 23:48:12
 */
@RestController
@RequestMapping("/api/admin/score")
public class ScoreAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;


    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(ScoreDto score, BasePage rowPage) {
        QueryWrapper<Score> wrapper = new MybatisWrapper<Score>()
                .like("u.username", score.getUsername())
                .like("u.name", score.getNickname())
                .like("g.no", score.getGoodsNo())
                .like("g.room", score.getGoodsRoom())
                .orderByDesc("create_time");

        if ("user".equals(loginUser().getRole())) {
            wrapper.eq("s.user_id", loginUserId());
        }

        PageInfo<ScoreDto> pageInfo = scoreService.pageDto(rowPage, wrapper);
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
        Score score = scoreService.getById(id);
        if (null == score) {
            score = new Score();
        }
        return JsonResponse.success(score);
    }

    /**
     * 更新
     *
     * @param score
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(Score score) {
        scoreService.saveOrUpdate(score);
        return JsonResponse.success();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        scoreService.removeById(id);
        return JsonResponse.success();
    }

    @GetMapping("my")
    public JsonResponse my() {
        Score score = scoreService.getOne("user_id", loginUserId());
        if (null == score) {
            score = new Score();
        }
        return JsonResponse.success(score);
    }
}
    


