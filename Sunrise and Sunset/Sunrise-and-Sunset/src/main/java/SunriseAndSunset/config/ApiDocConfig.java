package SunriseAndSunset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class ApiDocConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Sunrise and Sunset",
                "A Spring Boot application that gives the sunrise and sunset for specific longitude and latitude coordinates.",
                "1.0.0",
                null,
                new Contact("Nathan Young", "www.jointheleague.org", "nathaneric07@icloud.com"),
                null, null, Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}
