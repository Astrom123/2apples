package apples.gameschedule.service

import apples.gameschedule.entity.User
import apples.gameschedule.model.TimeIntervalDto
import apples.gameschedule.model.UserSchedule
import org.springframework.security.access.prepost.PreAuthorize

interface IScheduleService {
    fun getScheduleForWeek(): List<UserSchedule>
    fun getScheduleForUser(user: User): UserSchedule
    fun addIntervalForUser(intervalDto: TimeIntervalDto): TimeIntervalDto
    fun deleteInterval(intervalId: Long)
    fun updateInterval(intervalDto: TimeIntervalDto): TimeIntervalDto
}