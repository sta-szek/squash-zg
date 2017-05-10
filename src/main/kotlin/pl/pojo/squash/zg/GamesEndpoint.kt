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
    override fun getAllGames(): DeferredResult<Iterable<Game>> {
        val deferredResult = DeferredResult<Iterable<Game>>()
        CompletableFuture.supplyAsync(gamesService::getAll).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }

    @PostMapping(
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    override fun createNewGameFor(@RequestBody @Valid createGameBody: CreateGameBody): DeferredResult<Game> {
        val deferredResult = DeferredResult<Game>()
        CompletableFuture.supplyAsync({ gamesService.createNewGameFor(createGameBody.getGame(), createGameBody.userEmail) }).whenCompleteAsync({ result, _ ->
            deferredResult.setResult(result)
        })
        return deferredResult
    }
}





