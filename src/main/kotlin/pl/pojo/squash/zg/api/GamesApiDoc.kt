package pl.pojo.squash.zg.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.context.request.async.DeferredResult
import pl.pojo.squash.zg.model.Game
import javax.validation.Valid

@Api(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        tags = arrayOf("games")
)
interface GamesApiDoc {

    @ApiResponses(
            ApiResponse(code = 200, message = "List of games"),
            ApiResponse(code = 204, message = "Empty list")
    )
    fun getAllGames(): DeferredResult<Iterable<Game>>

    @ApiResponses(
            ApiResponse(code = 201, message = "Game created"),
            ApiResponse(code = 409, message = "?? user does not exist / game exist?")
    )
    fun createNewGameFor(@RequestBody @Valid createGameBody: CreateGameBody): DeferredResult<Game>
}