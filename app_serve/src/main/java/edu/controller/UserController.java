package edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.User;
import edu.service.UserService;
import edu.support.base.controller.BaseController;
import edu.support.resp.JsonResponse;
import edu.support.service.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author 执笔
 * @date 2021-03-29 17:46:28
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
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
    @RequestMapping("userList")
    public JsonResponse userList() {
        // {label:  value:}
        List<Map<String, Object>> list = userService.listMaps(new QueryWrapper<User>()
                .eq("role", "user")
                .select("id as value", "name as label"));
        return JsonResponse.success(list);
    }

}