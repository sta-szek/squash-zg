package pl.pojo.squash.zg.configuration

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.time.LocalTime


@EnableSwagger2
@Configuration
class SwaggerConfiguration {

    @Bean
    fun swaggerDocket() = Docket(DocumentationType.SWAGGER_2)
            .directModelSubstitute(LocalDate::class.java, String::class.java)
            .directModelSubstitute(LocalTime::class.java, String::class.java)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.or(PathSelectors.regex("/games*"), PathSelectors.regex("/users*")))
            .build()!!

}