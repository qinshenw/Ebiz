package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.User;
import edu.dto.req.UserPwdReq;
import edu.service.UserService;
import edu.support.base.controller.BaseController;
import edu.support.resp.JsonResponse;
import edu.support.service.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * 个人信息基本接口
 *
 * @author 执笔
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public JsonResponse login(String username, @RequestParam(defaultValue = "") String password) {
        log.info("用户登录" + username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("username", username)
                .eq("password", passwordEncoder.encode(password));
        User user = userService.getOne(queryWrapper);
        if (null == user) {
            return JsonResponse.fail("用户名或密码错误");
        } else {
            session.setAttribute("loginUser", user);
            session.setAttribute("loginUserId", user.getId());

            HashMap<Object, Object> data = new HashMap<>();
            data.put("user", user);
            data.put("permissions", new ArrayList<>());
            data.put("roles", Collections.singletonList(user.getRole()));
            return JsonResponse.success(data);
        }
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("register")
    public JsonResponse register(String username, @RequestParam(defaultValue = "") String password, @RequestParam(defaultValue = "") String code, String flag) {
        log.info("用户注册:" + username);
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        int count = userService.count(wrapper);
        if (count == 0) {
            User user = new User();
            user.setUsername(username);
            user.setRole("user");
            user.setName(username);
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
            return JsonResponse.success();
        } else {
            return JsonResponse.fail("该用户名已经存在");
        }
    }

    /**
     * 个人信息
     *
     * @return
     */
    @RequestMapping("info")
    public JsonResponse info(HttpServletRequest request) {
        User user = userService.getById(loginUserId());
        return JsonResponse.success(user);
    }

    /**
     * 更新个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    public JsonResponse update(User user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveOrUpdate(user);
        return JsonResponse.success(userService.getById(user.getId()));
    }


    /**
     * 修改密碼
     *
     * @param req
     * @return
     */
    @PostMapping("modifyPassword")
    public JsonResponse<String> modifyPassword(UserPwdReq req) {
        User user = userService.getById(loginUserId());
        if (!Objects.equals(req.getPwd(), req.getPwd2())) {
            return JsonResponse.fail("两次密码不一致");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return JsonResponse.fail("原密码错误");
        }

        user.setPassword(passwordEncoder.encode(req.getPwd()));
        userService.updateById(user);
        return JsonResponse.success("修改成功");
    }


    /**
     * 个人信息
     *
     * @return
     */
    @GetMapping("userInfo")
    public JsonResponse<User> userInfo() {
        User user = userService.getById(loginUserId());
        return JsonResponse.success(user);
    }

    @RequestMapping("logout")
    public JsonResponse logout(HttpServletRequest request) {
        return JsonResponse.success();
    }
}
