package com.antexploration.ant.InteractiveUtils.Interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringbootMVCConfig extends WebMvcConfigurationSupport {
    @Autowired
    MyHandlerInterceptor myHandlerInterceptor;

    /**
     * 自定义静态资源路径
     *
     * @return
     */
    public List<String> defineResourcePaths() {
        List<String> patterns = new ArrayList<>();
        patterns.add("/assets/**");
        patterns.add("/upload/**");
        patterns.add("/static/**");
        patterns.add("/common/**");
        patterns.add("/error");
        return patterns;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // 避开静态资源
        List<String> resourcePaths = defineResourcePaths();
        //token
        registry.addInterceptor(myHandlerInterceptor)
                // addPathPatterns 用于添加拦截规则 ， 先把所有路径都加入拦截， 再一个个排除
                .addPathPatterns("/**")
                // excludePathPatterns 表示改路径不用拦截---自添加加排除的登录注册路径
                .excludePathPatterns("/hry/Interactive/validateCode",

                        "/hry/Interactive/login",

                        "/hry/Interactive/validateAdminCode",

                        "/hry/Interactive/addMerchantTronWallet",

                        "/hry/Interactive/SignAdmin"

                );
        super.addInterceptors(registry);
    }

}
