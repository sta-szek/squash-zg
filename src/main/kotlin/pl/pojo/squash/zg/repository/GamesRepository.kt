package pl.pojo.squash.zg.repository

import org.springframework.data.repository.PagingAndSortingRepository
import pl.pojo.squash.zg.model.Game
import org.springframework.stereotype.Repository as Repo

@Repo
interface GamesRepository : PagingAndSortingRepository<Game, Long>
