package crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin()
                    .loginPage("/index")
                    .loginProcessingUrl("/login")
                    .usernameParameter("login")
                    .passwordParameter("password")
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
                        .hasRole("admin")
                    .antMatchers("/user/**")
                        .hasRole("user")
                .and()
                .httpBasic();
    }
}
