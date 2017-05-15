package pl.pojo.squash.zg

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersEndpoint(val usersRepository: UsersRepository) : UsersEndpointSwaggerDocumentation {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    override fun getAllUsers(): DeferredResult<Iterable<UserEntity>> {
        val deferredResult = DeferredResult<Iterable<UserEntity>>()
        CompletableFuture.supplyAsync(usersRepository::findAll).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }

    @PostMapping(
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    override fun createUser(@RequestBody @Valid createUserBody: CreateUserBody): DeferredResult<UserEntity> {
        if (usersRepository.findUserByEmail(createUserBody.email).isPresent) {
            throw UserAlreadyExistException()
        }
        val deferredResult = DeferredResult<UserEntity>()
        CompletableFuture.supplyAsync({ usersRepository.save(createUserBody.toUser()) }).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }
}


