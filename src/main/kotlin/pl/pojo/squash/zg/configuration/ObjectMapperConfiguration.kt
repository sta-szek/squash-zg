package pl.pojo.squash.zg.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.validation.ConstraintViolationProblemModule


@Configuration
class ObjectMapperConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerModule(ProblemModule())
            .registerModule(ConstraintViolationProblemModule())
}