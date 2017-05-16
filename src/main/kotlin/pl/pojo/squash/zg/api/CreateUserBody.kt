package pl.pojo.squash.zg.api;

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Email
import org.springframework.format.annotation.DateTimeFormat
import pl.pojo.squash.zg.model.Level
import pl.pojo.squash.zg.model.Sex
import pl.pojo.squash.zg.model.User
import java.time.LocalDate


data class CreateUserBody(@ApiModelProperty(example = "p.jonski@pojo.pl")
                          @field:Email
                          val email: String,

                          @ApiModelProperty(example = "123456789")
                          val phoneNumber: String? = null,

                          @ApiModelProperty(example = "Piotr")
                          val firstName: String? = null,

                          @ApiModelProperty(example = "Joński")
                          val lastName: String? = null,

                          @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          val birthDate: LocalDate? = null,

                          @ApiModelProperty(example = "MALE")
                          val sex: Sex = Sex.OTHER,

                          @ApiModelProperty(example = "BEGINNER")
                          val level: Level = Level.NONE) {

    fun toUser(): User = User(0, email, phoneNumber, firstName, lastName, birthDate, sex, level)

}
