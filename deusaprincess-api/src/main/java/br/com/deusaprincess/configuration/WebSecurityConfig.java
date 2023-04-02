package br.com.deusaprincess.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String URL_USER = "/user";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, URL_USER).hasAuthority("/user:POST")
                                .requestMatchers(HttpMethod.PUT, URL_USER).hasAuthority("/user:PUT")
                                .requestMatchers(HttpMethod.DELETE, URL_USER).hasAuthority("/user:DELETE")
                                .requestMatchers(HttpMethod.GET, URL_USER).hasAuthority("/user:GET")
                                .requestMatchers(HttpMethod.GET, URL_USER + "/{cpf}").hasAuthority("/user/{cpf}:GET")
                                .requestMatchers(HttpMethod.GET, URL_USER +"/email/{email}").hasAuthority("/user/email/{email}:GET")
                                .anyRequest().authenticated()
                ).csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
