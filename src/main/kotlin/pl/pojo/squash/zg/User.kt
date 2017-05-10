package pl.pojo.squash.zg;

import org.hibernate.validator.constraints.Email
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(@Id @field:Email @Column(unique = true) val email: String,
                val phoneNumber: String? = null,
                val firstName: String? = null,
                val lastName: String? = null,
                @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                val birthDate: LocalDate? = null,
                val sex: Sex = Sex.OTHER,
                val level: Level = Level.NONE)
