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
@RequestMapping("/games")
class GamesEndpoint(val gamesService: GamesService) : GamesEndpointSwaggerDocumentation {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    override fun getAllGames(): DeferredResult<Iterable<GameEntity>> {
        val deferredResult = DeferredResult<Iterable<GameEntity>>()
        CompletableFuture.supplyAsync(gamesService::getAll).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }

    @PostMapping(
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    override fun createNewGameFor(@RequestBody @Valid createGameBody: CreateGameBody): DeferredResult<GameEntity> {
        val deferredResult = DeferredResult<GameEntity>()
        CompletableFuture.supplyAsync({ gamesService.createNewGameFor(createGameBody.toGame(), createGameBody.userId) }).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }
}





