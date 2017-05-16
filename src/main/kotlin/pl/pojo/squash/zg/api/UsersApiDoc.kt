package pl.pojo.squash.zg.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.context.request.async.DeferredResult
import pl.pojo.squash.zg.model.User

@Api(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        tags = arrayOf("users")
)
interface UsersApiDoc {


    @ApiResponses(
            ApiResponse(code = 200, message = "List of users"),
            ApiResponse(code = 204, message = "Empty list")
    )
    fun getAllUsers(): DeferredResult<Iterable<User>>

    @ApiResponses(
            ApiResponse(code = 201, message = "User created"),
            ApiResponse(code = 409, message = "User with given email already exist")
    )
    fun createUser(@RequestBody createUserBody: CreateUserBody): DeferredResult<User>
}