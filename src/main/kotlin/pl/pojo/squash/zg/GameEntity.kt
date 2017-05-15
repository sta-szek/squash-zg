package pl.pojo.squash.zg

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.DayOfWeek
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "games")
data class GameEntity(@Id @GeneratedValue @Column(unique = true) val id: Long = 0,
                      val dayOfWeek: DayOfWeek,
                      @Column(name = "startTime")
                      val from: LocalTime,
                      val to: LocalTime,
                      @ManyToOne(fetch = FetchType.LAZY, optional = false)
                      @JsonIgnore
                      var userEntity: UserEntity? = null)