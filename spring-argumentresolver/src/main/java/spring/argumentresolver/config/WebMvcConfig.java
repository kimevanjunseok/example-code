package spring.argumentresolver.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import spring.argumentresolver.controller.argumentresolver.AuthArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;

    public WebMvcConfig(final AuthArgumentResolver authArgumentResolver) {
        this.authArgumentResolver = authArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
