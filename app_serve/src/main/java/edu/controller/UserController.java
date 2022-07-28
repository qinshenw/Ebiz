package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.User;
import edu.service.UserService;
import edu.support.base.controller.BaseController;
import edu.support.resp.JsonResponse;
import edu.support.service.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Authentication
 *
 * @author wqs
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * User login
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public JsonResponse login(String username, @RequestParam(defaultValue = "") String password) {
        log.info("User login" + username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("username", username)
                .eq("password", passwordEncoder.encode(password));
        User user = userService.getOne(queryWrapper);
        if (null == user) {
            return JsonResponse.fail("Username or password wrong");
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
     * Register
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("register")
    public JsonResponse register(String username, @RequestParam(defaultValue = "") String password, @RequestParam(defaultValue = "") String code, String flag) {
        log.info("User register:" + username);
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
            return JsonResponse.fail("Username existed");
        }
    }

    @RequestMapping("logout")
    public JsonResponse logout(HttpServletRequest request) {
        return JsonResponse.success();
    }
}
