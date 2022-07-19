package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.Notice;
import edu.service.NoticeService;
import edu.support.base.controller.BaseController;
import edu.support.base.BasePage;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Notice)表控制层
 *
 * @author 执笔
 * @date 2021-05-07 23:48:11
 */
@RestController
@RequestMapping("/api/admin/notice")
public class NoticeAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private NoticeService noticeService;


    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(Notice notice, BasePage rowPage) {
        QueryWrapper<Notice> wrapper = new MybatisWrapper<Notice>()
                .like("title", notice.getTitle())
                .orderByDesc("create_time");

        PageInfo<Notice> pageInfo = noticeService.page(rowPage, wrapper);
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
        Notice notice = noticeService.getById(id);
        if (null == notice) {
            notice = new Notice();
        }
        return JsonResponse.success(notice);
    }

    /**
     * 更新
     *
     * @param notice
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(Notice notice) {
        noticeService.saveOrUpdate(notice);
        return JsonResponse.success();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        noticeService.removeById(id);
        return JsonResponse.success();
    }
}
    


