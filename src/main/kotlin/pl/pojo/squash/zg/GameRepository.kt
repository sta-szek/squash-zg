package pl.pojo.squash.zg

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : PagingAndSortingRepository<Game, Long>
