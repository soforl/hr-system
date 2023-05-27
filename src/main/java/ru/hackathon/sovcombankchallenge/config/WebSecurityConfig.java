package ru.hackathon.sovcombankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeHttpRequests((requests) -> requests


                        // Отркываю доступ к свагеру всем!
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**" ).permitAll()
                        // Доступ к контроллеру USer
                        .requestMatchers("/api/user/registration/", "/api/user/registration").permitAll()
                        // Запрещаю все остальное

                        .anyRequest().permitAll()


                )
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}