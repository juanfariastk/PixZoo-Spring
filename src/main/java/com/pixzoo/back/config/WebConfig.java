package com.pixzoo.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pixzoo.back.interceptor.UniqueCpfInterceptor;
//import com.pixzoo.back.interceptor.UniqueEmailInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UniqueCpfInterceptor uniqueCpfInterceptor;
   // private final UniqueEmailInterceptor uniqueEmailInterceptor;

    @Autowired
    public WebConfig(UniqueCpfInterceptor uniqueCpfInterceptor/*, UniqueEmailInterceptor uniqueEmailInterceptor*/) {
        this.uniqueCpfInterceptor = uniqueCpfInterceptor;
        //this.uniqueEmailInterceptor = uniqueEmailInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(uniqueCpfInterceptor);
        //registry.addInterceptor(uniqueEmailInterceptor);
    }
}
