package pl.pojo.squash.zg.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import pl.pojo.squash.zg.model.User
import pl.pojo.squash.zg.service.UsersService
import java.util.concurrent.CompletableFuture.supplyAsync
import javax.validation.Valid


@RestController
@RequestMapping("/users")
class UsersApi(val usersService: UsersService) : UsersApiDoc {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    override fun getAllUsers(): DeferredResult<Iterable<User>> {
        val deferredResult = DeferredResult<Iterable<User>>()

        supplyAsync(usersService::getAll)
                .whenCompleteAsync({ result, _ -> deferredResult.setResult(result) })

        return deferredResult
    }

    @PostMapping(
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    override fun createUser(@RequestBody @Valid createUserBody: CreateUserBody): DeferredResult<User> {
        val deferredResult = DeferredResult<User>()
        supplyAsync({ usersService.save(createUserBody.toUser()) })
                .whenCompleteAsync({ result, _ -> deferredResult.setResult(result) })
        return deferredResult
    }
}


