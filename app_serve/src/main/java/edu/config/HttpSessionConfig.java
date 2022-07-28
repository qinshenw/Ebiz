package edu.config;

import edu.config.session.HeaderCookieHttpSessionIdResolver;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * session configuration  specify session by request header
 *
 * @author wqs
 * @date 13:39
 */
@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class HttpSessionConfig {

    /**
     * X-Auth-Token
     *
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderCookieHttpSessionIdResolver();
    }
}