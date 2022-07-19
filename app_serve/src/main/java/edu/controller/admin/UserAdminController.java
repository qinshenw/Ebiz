package edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.domain.User;
import edu.service.UserService;
import edu.support.base.BasePage;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import edu.support.service.PasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author 执笔
 * @date 2021-03-29 17:46:28
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserAdminController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 列表
     *
     * @return
     */
    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonResponse list(User user, BasePage rowPage) {
        QueryWrapper<User> wrapper = new MybatisWrapper<User>()
                .ne("role", "admin")
                .like("name", user.getName())
                .like("username", user.getUsername())
                .orderByDesc("create_time");

        PageInfo<User> pageInfo = userService.page(rowPage, wrapper);
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
        User user = userService.getById(id);
        if (null == user) {
            user = new User();
        }
        return JsonResponse.success(user);
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        User one = userService.getOne("username", user.getUsername());
        if (null != one && !one.getId().equals(user.getId())) {
            return JsonResponse.fail("该编号已经存在");
        }
        userService.saveOrUpdate(user);
        return JsonResponse.success();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequestMapping("delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        userService.removeById(id);
        return JsonResponse.success();
    }
}