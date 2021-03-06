package apples.gameschedule.repository;

import apples.gameschedule.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUsername(username: String): User
}