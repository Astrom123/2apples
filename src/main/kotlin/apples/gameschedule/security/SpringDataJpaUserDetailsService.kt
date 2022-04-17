package apples.gameschedule.security

import apples.gameschedule.repository.UserRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


@Component
class SpringDataJpaUserDetailsService(val repository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(name: String): UserDetails {
        val user = repository.findUserByUsername(name)
        return UserDetails(user.id,
            user.name, user.password,
            AuthorityUtils.createAuthorityList(user.role)
        )
    }
}