package pl.pojo.squash.zg.model;

import org.hibernate.validator.constraints.Email
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
data class User(@Id @GeneratedValue @Column(unique = true) val id: Long,
                @field:Email @Column(unique = true) val email: String,
                val phoneNumber: String? = null,
                val firstName: String? = null,
                val lastName: String? = null,
                val birthDate: java.time.LocalDate? = null,
                val sex: Sex = Sex.OTHER,
                val level: Level = Level.NONE)
