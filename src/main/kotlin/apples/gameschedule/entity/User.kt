package apples.gameschedule.entity

import apples.gameschedule.model.TimeIntervalDto
import org.hibernate.Hibernate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long,

    val username: String,

    password: String,

    val role: String,

    @Column(nullable = false)
    val name: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var timeIntervals: Set<TimeInterval>
) {
    val password: String = PASSWORD_ENCODER.encode(password)

    companion object {
        val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name)
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, role = $role , name = $name )"
    }
}
