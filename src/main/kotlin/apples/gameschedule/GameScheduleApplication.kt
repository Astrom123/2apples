package apples.gameschedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameScheduleApplication

fun main(args: Array<String>) {
	runApplication<GameScheduleApplication>(*args)
}
