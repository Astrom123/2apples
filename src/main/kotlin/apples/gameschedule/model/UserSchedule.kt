package apples.gameschedule.model

data class UserSchedule(val user: UserDto,
                        val timeIntervals: Map<Int, List<TimeIntervalDto>>
)
