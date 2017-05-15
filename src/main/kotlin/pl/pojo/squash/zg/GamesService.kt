package pl.pojo.squash.zg

import org.springframework.stereotype.Service

@Service
class GamesService(val gameRepository: GameRepository, val usersRepository: UsersRepository) {

    fun getAll(): Iterable<GameEntity> = gameRepository.findAll()

    fun createNewGameFor(game: GameEntity, userId: Long): GameEntity {
        val user = usersRepository.findOne(userId)
        if (!user.isPresent) {
            throw UserNotExistException()
        } else {
            game.userEntity = user.get()
            return gameRepository.save(game)
        }
    }
}