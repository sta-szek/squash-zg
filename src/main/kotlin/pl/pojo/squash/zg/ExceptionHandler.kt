package pl.pojo.squash.zg

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.spring.web.advice.ProblemHandling
import javax.ws.rs.core.Response


@ControllerAdvice
class ExceptionHandler : ProblemHandling {

    @ExceptionHandler(UserAlreadyExistException::class)
    fun handleUserAlreadyExistException(exception: UserAlreadyExistException, nativeWebRequest: NativeWebRequest) =
            create(Problem.builder()
                    .withStatus(Response.Status.CONFLICT)
                    .build(),
                    nativeWebRequest)!!
}
