package pl.pojo.squash.zg.repository

import org.springframework.data.repository.Repository
import pl.pojo.squash.zg.model.User
import java.util.*
import org.springframework.stereotype.Repository as Repo

@Repo
interface UsersRepository : Repository<User, Long> {
    fun findUserByEmail(email: String): Optional<User>
    fun findOne(id: Long?): Optional<User>
    fun save(entity: User): User
    fun findAll(): Iterable<User>
}
