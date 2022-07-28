package com.page_service.Config;

import com.page_service.Interceptor.RegisterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置类，注入适配器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private RegisterInterceptor registerInterceptor;
    /**
     * addInterceptors方法，addInterceptor方法是将拦截器注入到适配器中。
     * addPathPatterns方法是设置一个需要拦截的路径，可以是多个字符串或者是直接传入一个数组。
     * excludePathPatterns是配置不需要拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        //不拦截静态资源
                        "/bootstrap-3.3.7-dist/**","/css/**","/fonts/**",
                        "/img/**","/js/**","/layer/**","/vue/**","/vuemin/**","/axios/**","/get/photo/**",
                        //不拦截的路径
                        "/","/index","/cinema","/cinema/search","/mine","/index/tosearch","/index/detail","/mine/login",
                        "/mine/tologin", "/mine/register","/mine/toregister", "/mine/oversea_phone","/index/search","/alipayNotifyNotice","/alipayReturnNotice"
                        );
    }
}
