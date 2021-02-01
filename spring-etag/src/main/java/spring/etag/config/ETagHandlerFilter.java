package spring.etag.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class ETagHandlerFilter {

    // @Bean
    // public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
    //     return new ShallowEtagHeaderFilter();
    // }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
                = new FilterRegistrationBean<>( new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/posts/etag");
        filterRegistrationBean.setName("PostAPIFilter");
        return filterRegistrationBean;
    }
}
