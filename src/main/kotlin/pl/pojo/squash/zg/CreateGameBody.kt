package pl.pojo.squash.zg

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Email
import org.springframework.format.annotation.DateTimeFormat
import java.time.DayOfWeek
import java.time.LocalTime


class CreateGameBody(val dayOfWeek: DayOfWeek,
                     @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                     val from: LocalTime,
                     @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                     val to: LocalTime,
                     @field:Email val userEmail: String) {

    fun getGame(): Game = Game(dayOfWeek = this.dayOfWeek, from = this.from, to = this.to)

    @JsonCreator
    fun createGameBody(@JsonProperty("dayOfWeek") dayOfWeek: DayOfWeek,
                       @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                       @JsonProperty("from") from: LocalTime,
                       @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                       @JsonProperty("to") to: LocalTime,
                       @Email @JsonProperty("userEmail") userEmail: String): CreateGameBody = CreateGameBody(dayOfWeek, from, to, userEmail)
}