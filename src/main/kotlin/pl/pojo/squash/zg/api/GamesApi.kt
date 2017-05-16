package pl.pojo.squash.zg.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import pl.pojo.squash.zg.model.Game
import pl.pojo.squash.zg.service.GamesService
import java.util.concurrent.CompletableFuture.supplyAsync
import javax.validation.Valid

@RestController
@RequestMapping("/games")
class GamesApi(val gamesService: GamesService) : GamesApiDoc {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    override fun getAllGames(): DeferredResult<Iterable<Game>> {
        val deferredResult = DeferredResult<Iterable<Game>>()

        supplyAsync(gamesService::getAll)
                .whenCompleteAsync({ result, _ -> deferredResult.setResult(result) })

        return deferredResult
    }

    @PostMapping(
            consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    override fun createNewGameFor(@RequestBody @Valid createGameBody: CreateGameBody): DeferredResult<Game> {
        val deferredResult = DeferredResult<Game>()

        supplyAsync({ gamesService.createNewGameFor(createGameBody.toGame(), createGameBody.userId) })
                .whenCompleteAsync({ result, _ -> deferredResult.setResult(result) })

        return deferredResult
    }
}





