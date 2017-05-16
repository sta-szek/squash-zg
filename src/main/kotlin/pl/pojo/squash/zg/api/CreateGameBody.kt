package pl.pojo.squash.zg.api

import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import pl.pojo.squash.zg.model.Game
import javax.validation.constraints.Min


data class CreateGameBody(@ApiModelProperty(example = "MONDAY")
                          val dayOfWeek: java.time.DayOfWeek,

                          @ApiModelProperty(example = "12:00")
                          @field:DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                          val from: java.time.LocalTime,

                          @ApiModelProperty(example = "13:00")
                          @field:DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                          val to: java.time.LocalTime,

                          @ApiModelProperty(example = "1")
                          @field:Min(1)
                          val userId: Long) {

    fun toGame(): Game = Game(0, dayOfWeek, from, to)

}