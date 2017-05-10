package pl.pojo.squash.zg

import org.springframework.stereotype.Service

@Service
class GamesService(val gameRepository: GameRepository, val usersRepository: UsersRepository) {

    fun getAll(): Iterable<Game> = gameRepository.findAll()

    fun createNewGameFor(game: Game, userEmail: String): Game {
        val user = usersRepository.findOne(userEmail)
        if(user==null) {
            throw UserNotExistException()
        } else {
            game.user = user
            return gameRepository.save(game)
        }
    }
}