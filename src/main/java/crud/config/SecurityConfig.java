package crud.config;

import crud.security.AuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthHandler authHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin()
                    .loginPage("/index")
                    .loginProcessingUrl("/login")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .successHandler(authHandler)
                .permitAll()
                .and()
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .logoutUrl("/index")
                    .logoutSuccessUrl("/index")
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/**")
                        .hasAuthority("admin")//посмотреть
                    .antMatchers("/user/**")
                        .hasRole("user")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthHandler getAuthHandler() {
        return new AuthHandler();
    }
}

//добавить passwordEncoder
//d security authentifHandler
