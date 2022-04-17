package apples.gameschedule.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @OneToMany()
    val timeIntervals: Set<TimeInterval>
)
