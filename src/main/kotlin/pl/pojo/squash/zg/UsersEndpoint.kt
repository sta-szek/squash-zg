package pl.pojo.squash.zg

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.CompletableFuture

@RestController("/users")
class UsersEndpoint(val usersRepository: UsersRepository) {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getAllUsers(): DeferredResult<MutableIterable<User>>? {
        val deferredResult = DeferredResult<MutableIterable<User>>()
        CompletableFuture.supplyAsync(usersRepository::findAll).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): DeferredResult<User>? {
        if (usersRepository.exists(user.email)) {
            throw UserAlreadyExistException()
        }
        val deferredResult = DeferredResult<User>()
        CompletableFuture.supplyAsync({ usersRepository.save(user) }).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }
}


