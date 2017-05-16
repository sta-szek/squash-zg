package pl.pojo.squash.zg

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import pl.pojo.squash.zg.model.Game
import pl.pojo.squash.zg.model.User
import pl.pojo.squash.zg.repository.GamesRepository
import pl.pojo.squash.zg.repository.UsersRepository
import java.time.DayOfWeek
import java.time.LocalTime

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val applicationContext = SpringApplication.run(Application::class.java, *args)

    val usersRepository = applicationContext.getBean("usersRepository") as UsersRepository
    val user1 = usersRepository.save(User(0, "p.jonski@pojo.pl"))
    val user2 = usersRepository.save(User(0, "p.joski@pojo.pl"))
    val user3 = usersRepository.save(User(0, "p.jski@pojo.pl"))

    val gamesRepository = applicationContext.getBean("gamesRepository") as GamesRepository
    val game1 = Game(0, DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now().plusHours(1), user1)
    val game4 = Game(0, DayOfWeek.MONDAY, LocalTime.now(), LocalTime.now().plusHours(1), user2)
    val game2 = Game(0, DayOfWeek.WEDNESDAY, LocalTime.now(), LocalTime.now().plusHours(1), user1)
    val game5 = Game(0, DayOfWeek.WEDNESDAY, LocalTime.now(), LocalTime.now().plusHours(1), user3)
    val game3 = Game(0, DayOfWeek.SUNDAY, LocalTime.now(), LocalTime.now().plusHours(1), user1)
    gamesRepository.save(game1)
    gamesRepository.save(game2)
    gamesRepository.save(game3)
    gamesRepository.save(game4)
    gamesRepository.save(game5)

}
