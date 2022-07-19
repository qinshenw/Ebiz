package edu.config;

import edu.config.session.HeaderCookieHttpSessionIdResolver;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * session 配置  使用请求头来确定session
 * 过期的会自动清除
 *
 * @author 执笔
 * @date 13:39
 */
@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class HttpSessionConfig {

    /**
     * 请求头里面
     * X-Auth-Token
     *
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderCookieHttpSessionIdResolver();
    }
}