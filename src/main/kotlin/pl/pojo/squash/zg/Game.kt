package pl.pojo.squash.zg

import java.time.DayOfWeek
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Game(@Id @Column(unique = true) @GeneratedValue var id: Long = 0,
                val dayOfWeek: DayOfWeek,
                val from: LocalTime,
                val to: LocalTime,
                @ManyToOne(fetch = FetchType.LAZY, optional = false)
                var user: User? = null)