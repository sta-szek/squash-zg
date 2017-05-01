package pl.pojo.squash.zg;

import org.hibernate.validator.constraints.Email
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(@Id @Email @Column(unique = true) val email: String,
                val firstName: String? = null,
                val lastName: String? = null)