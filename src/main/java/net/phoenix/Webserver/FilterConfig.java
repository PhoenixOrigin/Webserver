package net.phoenix.Webserver;

import net.phoenix.Webserver.utils.ApiFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<ApiFilter> apiFilter() {
        FilterRegistrationBean<ApiFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}





