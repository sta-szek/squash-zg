package pl.pojo.squash.zg.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "games")
data class Game(@Id @GeneratedValue @Column(unique = true) val id: Long = 0,
                val dayOfWeek: java.time.DayOfWeek,
                @Column(name = "startTime")
                val from: java.time.LocalTime,
                val to: java.time.LocalTime,
                @ManyToOne(fetch = FetchType.LAZY, optional = false)
                @JsonIgnore
                var user: User? = null)