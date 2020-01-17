package com.hoangmn.config;

import com.hoangmn.filter.AdminFilter;
import com.hoangmn.filter.HomeFilter;
import com.hoangmn.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Autowired
    private HomeFilter homeFilter;

    @Autowired
    private UserFilter userFilter;

    @Autowired
    private AdminFilter adminFilter;

    @Bean
    public FilterRegistrationBean<HomeFilter> homeFilterRegistrationBean() {
        FilterRegistrationBean<HomeFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(homeFilter);
        registrationBean.addUrlPatterns("/");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<UserFilter> userFilterRegistrationBean() {
        FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(userFilter);
        registrationBean.addUrlPatterns("/user", "/user/movies", "/user/movies/add", "/user/movies/remove");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/admin", "/admin/manageusers",
                                        "/admin/manageusers/add", "/admin/manageusers/update",
                                        "/admin/manageusers/delete",
                "/admin/managemovies",
                "/admin/managemovies/add", "/admin/managemovies/update",
                "/admin/managemovies/delete");
        registrationBean.setOrder(3);
        return registrationBean;
    }

}
