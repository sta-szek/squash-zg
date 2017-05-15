package pl.pojo.squash.zg;

import org.hibernate.validator.constraints.Email
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(@Id @GeneratedValue @Column(unique = true) val id: Long,
                      @field:Email @Column(unique = true) val email: String,
                      val phoneNumber: String? = null,
                      val firstName: String? = null,
                      val lastName: String? = null,
                      val birthDate: LocalDate? = null,
                      val sex: Sex = Sex.OTHER,
                      val level: Level = Level.NONE)
