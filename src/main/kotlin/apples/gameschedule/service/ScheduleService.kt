package apples.gameschedule.service

import apples.gameschedule.entity.TimeInterval
import apples.gameschedule.entity.User
import apples.gameschedule.model.TimeIntervalDto
import apples.gameschedule.model.UserDto
import apples.gameschedule.model.UserSchedule
import apples.gameschedule.repository.TimeIntervalRepository
import apples.gameschedule.repository.UserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val userRepository: UserRepository,
    private val timeIntervalRepository: TimeIntervalRepository
) : IScheduleService {

    override fun getScheduleForWeek(): List<UserSchedule> {
        val users = userRepository.findAll()

        return users.map { getScheduleForUser(it) }
    }

    override fun getScheduleForUser(user: User): UserSchedule {
        val timeIntervals = user.timeIntervals.map { it.toDto() }

        return UserSchedule(UserDto(user.id, user.name), timeIntervals.groupBy { it.dayOfWeek })
    }

    @PreAuthorize("#intervalDto.userId == authentication.principal.id")
    override fun addIntervalForUser(intervalDto: TimeIntervalDto): TimeIntervalDto {
        val user = userRepository.findById(intervalDto.userId).get()
        val intervalEntity = TimeInterval(
            dayOfWeek = intervalDto.dayOfWeek,
            startTime = intervalDto.startTime,
            endTime = intervalDto.endTime,
            user = user
        )

        return timeIntervalRepository.saveAndFlush(intervalEntity).toDto()
    }

    override fun deleteInterval(intervalId: Long) {
        val user = timeIntervalRepository.findById(intervalId).get().user
        deleteIntervalForUser(user, intervalId)
    }

    @PreAuthorize("#user.id == authentication.principal.id")
    private fun deleteIntervalForUser(user: User, intervalId: Long) {
        timeIntervalRepository.deleteById(intervalId)
    }

    @PreAuthorize("#intervalDto.userId == authentication.principal.id")
    override fun updateInterval(intervalDto: TimeIntervalDto): TimeIntervalDto {
        val interval = intervalDto.id?.let { timeIntervalRepository.getById(it) } ?: throw java.lang.RuntimeException("interval not found")
        interval.dayOfWeek = intervalDto.dayOfWeek
        interval.startTime = intervalDto.startTime
        interval.endTime = intervalDto.endTime

        return timeIntervalRepository.saveAndFlush(interval).toDto()
    }
}