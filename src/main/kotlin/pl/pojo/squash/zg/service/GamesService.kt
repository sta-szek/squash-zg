package pl.pojo.squash.zg.service

import org.springframework.stereotype.Service
import pl.pojo.squash.zg.model.Game
import pl.pojo.squash.zg.repository.GamesRepository
import pl.pojo.squash.zg.repository.UsersRepository

@Service
class GamesService(val gamesRepository: GamesRepository, val usersRepository: UsersRepository) {

    fun getAll(): Iterable<Game> = gamesRepository.findAll()

    fun createNewGameFor(game: Game, userId: Long): Game {
        val user = usersRepository.findOne(userId)
        if (!user.isPresent) {
            throw UserNotExistException()
        } else {
            game.user = user.get()
            return gamesRepository.save(game)
        }
    }
}