package pl.pojo.squash.zg.service

import org.springframework.stereotype.Service
import pl.pojo.squash.zg.model.User
import pl.pojo.squash.zg.repository.UsersRepository

@Service
class UsersService(val usersRepository: UsersRepository) {

    fun getAll(): Iterable<User> = usersRepository.findAll()

    fun save(user: User): User {
        if (usersRepository.findUserByEmail(user.email).isPresent) {
            throw UserAlreadyExistException()
        } else {
            return usersRepository.save(user)
        }
    }
}
