package edu.config.interceptor;

import cn.hutool.json.JSONUtil;
import edu.support.resp.JsonResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login interceptor
 *
 * @author wqs
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object attribute = request.getSession().getAttribute("loginUser");
        if (null == attribute) {

            String utf8 = "utf-8";
            String application = "application/json";
            try {
                response.setContentType(application);
                response.setCharacterEncoding(utf8);
                response.getWriter().print(JSONUtil.toJsonStr(JsonResponse.noLogin()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            return true;
        }
    }

}