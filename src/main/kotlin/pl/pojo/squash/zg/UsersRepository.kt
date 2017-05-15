package pl.pojo.squash.zg

import org.springframework.data.repository.Repository
import java.util.*
import org.springframework.stereotype.Repository as Repo

@Repo
interface UsersRepository : Repository<UserEntity, Long> {
    fun findUserByEmail(email: String): Optional<UserEntity>
    fun findOne(id: Long?): Optional<UserEntity>
    fun save(entity: UserEntity): UserEntity
    fun findAll(): Iterable<UserEntity>
}
