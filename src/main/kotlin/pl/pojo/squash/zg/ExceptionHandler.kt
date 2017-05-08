package pl.pojo.squash.zg

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.spring.web.advice.ProblemHandling
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem
import javax.ws.rs.core.Response


@ControllerAdvice
class ExceptionHandler : ProblemHandling {

    @ExceptionHandler(UserAlreadyExistException::class)
    fun handleUserAlreadyExistException(exception: UserAlreadyExistException, nativeWebRequest: NativeWebRequest) =
            create(Problem.builder()
                    .withStatus(Response.Status.CONFLICT)
                    .build(),
                    nativeWebRequest)!!

    override fun process(entity: ResponseEntity<Problem>?): ResponseEntity<Problem> {
        val problem = entity!!.body
        val headers = entity.headers
        val problemWithoutStackTrace = createProblemWithoutStackTrace(problem)

        return ResponseEntity.status(problem.status!!.statusCode)
                .headers(headers)
                .contentType(headers.contentType)
                .body(problemWithoutStackTrace)
    }

    private fun createProblemWithoutStackTrace(problem: Problem): DefaultProblem {
        val parameters = HashMap<String,Any>()
        if (problem is ConstraintViolationProblem) {
            parameters.put("violations", problem.violations)
        }
        return DefaultProblem(
                type = problem.type,
                title = problem.title,
                status = problem.status,
                parameters = parameters,
                instance = problem.instance,
                detail = problem.detail)
    }
}
