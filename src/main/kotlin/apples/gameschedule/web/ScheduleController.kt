package apples.gameschedule.web

import apples.gameschedule.model.TimeIntervalDto
import apples.gameschedule.model.UserSchedule
import apples.gameschedule.service.IScheduleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/2apples/api/schedule")
class ScheduleController(private val scheduleService: IScheduleService) {

    @GetMapping
    fun getSchedule(): List<UserSchedule> {
        return scheduleService.getScheduleForWeek()
    }

    @PostMapping("/interval")
    fun addTimeInterval(@RequestBody intervalDto: TimeIntervalDto): TimeIntervalDto {
        return scheduleService.addIntervalForUser(intervalDto)
    }

    @DeleteMapping("/interval/{intervalId}")
    fun deleteTimeInterval(@PathVariable intervalId: Long) {
        scheduleService.deleteInterval(intervalId)
    }

    @PutMapping("/interval")
    fun updateTimeInterval(@RequestBody intervalDto: TimeIntervalDto): TimeIntervalDto {
        return scheduleService.updateInterval(intervalDto)
    }
}