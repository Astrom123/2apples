package apples.gameschedule.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class TimeIntervalDto(
    val id: Long?,
    val userId: Long,
    @JsonFormat(pattern = "HH:mm")
    val startTime: LocalTime,
    @JsonFormat(pattern = "HH:mm")
    val endTime: LocalTime,
    val dayOfWeek: Int
)