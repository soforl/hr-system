package ru.hackathon.sovcombankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()

                .authorizeHttpRequests((requests) -> requests


                        // Отркываю доступ к свагеру всем!
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**" ).permitAll()
                        // Доступ к контроллеру USer
                        .requestMatchers("/api/user/registration/", "/api/user/registration").permitAll()
                        // Запрещаю все остальное
                                .anyRequest().authenticated()

                )

                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}