package pl.pojo.squash.zg

import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.DayOfWeek
import java.time.LocalTime
import javax.validation.constraints.Min


data class CreateGameBody(@ApiModelProperty(example = "MONDAY") val dayOfWeek: DayOfWeek,
                          @ApiModelProperty(example = "12:00") @field:DateTimeFormat(iso = DateTimeFormat.ISO.TIME) val from: LocalTime,
                          @ApiModelProperty(example = "13:00") @field:DateTimeFormat(iso = DateTimeFormat.ISO.TIME) val to: LocalTime,
                          @ApiModelProperty(example = "1") @field:Min(1) val userId: Long) {

    fun toGame(): GameEntity = GameEntity(0, dayOfWeek, from, to)

}