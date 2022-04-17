package apples.gameschedule.repository

import apples.gameschedule.entity.TimeInterval
import apples.gameschedule.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimeIntervalRepository : JpaRepository<TimeInterval, Long>