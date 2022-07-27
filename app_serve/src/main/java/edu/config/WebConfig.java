package edu.config;


import com.gitee.zhibi.fast.commons.base.Constants;
import edu.config.interceptor.HttpResponseInterceptorHandler;
import edu.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 *
 * @author 执笔
 * @date 14:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${fast.upload.path:}")
    private String filePath;

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private HttpResponseInterceptorHandler httpResponseInterceptorHandler;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 方便前端获取
                .exposedHeaders("X-Auth-Token")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpResponseInterceptorHandler).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/api/login", "/api/register", "/api/logout",
                        "/api/ueditor/**",
                        "/api/common/**", "/api/upload/**", "/api/report/**", "/api/model/**")
                .addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + Constants.Upload.PREFIX + "/**").addResourceLocations("file:" + filePath + Constants.Upload.PREFIX + "/");
        registry.addResourceHandler("/api/upload/**").addResourceLocations("file:" + filePath + Constants.Upload.PREFIX + "/");
    }


}
