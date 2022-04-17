package apples.gameschedule.security

import apples.gameschedule.entity.User
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfiguration(private val userDetailsService: SpringDataJpaUserDetailsService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(User.PASSWORD_ENCODER)
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/built/**", "/api/docs/**", "/main.css").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/", true)
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .logout()
            .logoutSuccessUrl("/")
    }
}