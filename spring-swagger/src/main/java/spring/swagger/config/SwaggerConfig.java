package spring.swagger.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiV1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo1())
                .groupName("groupName1")
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("spring.swagger.controller"))
                .paths(PathSelectors.ant("/posts/**")).build();
    }

    private ApiInfo apiInfo1() {
        return new ApiInfo(
                "title",
                "description",
                "version",
                "https://velog.io/@tigger",
                new Contact("Contact Me", "https://velog.io/@tigger", "tigger@tigger.com"),
                "tigger Licenses",
                "https://github.com/kimevanjunseok",
                new ArrayList<>()
        );
    }

    @Bean
    public Docket apiV2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("groupName2")
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("spring.swagger.controller"))
                .paths(PathSelectors.ant("/posts/**")).build();
    }
}
