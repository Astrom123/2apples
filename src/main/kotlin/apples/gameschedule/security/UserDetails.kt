package apples.gameschedule.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class UserDetails : User {
    val id: Long

    constructor(id: Long, username: String?, password: String?, authorities: Collection<GrantedAuthority?>?) : super(username, password, authorities) {
        this.id = id
    }

    constructor(
        id: Long, username: String?, password: String?,
        enabled: Boolean, accountNonExpired: Boolean,
        credentialsNonExpired: Boolean,
        accountNonLocked: Boolean,
        authorities: Collection<GrantedAuthority?>?
    ) : super(
        username, password, enabled,
        accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities
    ) {
        this.id = id
    }
}