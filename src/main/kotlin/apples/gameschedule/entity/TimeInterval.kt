package apples.gameschedule.entity

import apples.gameschedule.model.TimeIntervalDto
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "time_intervals")
class TimeInterval(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "day_of_week")
    var dayOfWeek: Int,

    @Column(name = "start_time")
    var startTime: LocalTime,

    @Column(name = "end_time")
    var endTime: LocalTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User
) {
    fun toDto(): TimeIntervalDto {
        return TimeIntervalDto(id, user.id, startTime, endTime, dayOfWeek)
    }
}