package crud.config;

import crud.security.AuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("crud")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin()
                    .loginPage("/admin/users")
                    .loginProcessingUrl("/log")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    //.failureUrl("/auth")
                    .successHandler(getAuthHandler())
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .logoutUrl("/out")
                    .logoutSuccessUrl("/auth")
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/**")
                        .hasAuthority("admin")//посмотреть
                    .antMatchers("/user/**")
                        .hasRole("user");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthHandler getAuthHandler() {
        return new AuthHandler();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}


