package com.hoangmn.config;

import com.hoangmn.filter.AuthenticationFilter;
import com.hoangmn.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/", "/app/*", "/admin/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/app/*", "/admin/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
