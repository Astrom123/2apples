package apples.gameschedule

import apples.gameschedule.entity.TimeInterval
import apples.gameschedule.entity.User
import apples.gameschedule.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class DatabaseLoader(val userRepository: UserRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val user = User(id = 1, username = "2apples", password = "2apples", role = "ROLE_ADMIN", name = "2apples", timeIntervals = emptySet())
        user.timeIntervals = getTimeIntervals(user)
        userRepository.save(user)
    }

    fun getTimeIntervals(user: User): Set<TimeInterval> {
        val intervals = mutableSetOf<TimeInterval>()
        for (i in 1..7) {
            intervals.add(TimeInterval(dayOfWeek = i, startTime = LocalTime.now(), endTime = LocalTime.now().plusHours(1), user = user))
        }

        return intervals
    }
}