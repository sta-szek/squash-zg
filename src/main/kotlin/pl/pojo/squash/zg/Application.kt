package pl.pojo.squash.zg

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val run = SpringApplication.run(Application::class.java, *args)

    val bean = run.getBean ("usersRepository") as UsersRepository
    bean.save(UserEntity(0,"p.jonski@pojo.pl"))
    bean.save(UserEntity(0,"p.joski@pojo.pl"))
    bean.save(UserEntity(0,"p.jski@pojo.pl"))

}
