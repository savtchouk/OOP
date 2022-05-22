package ru.spbstu.courseworkserver.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.spbstu.courseworkserver.main.security.jwt.JwtSecurityConfigurer;
import ru.spbstu.courseworkserver.main.security.jwt.JwtTokenProvider;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/add-user").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/auth/delete-user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST).hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT).hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));

    }
}
