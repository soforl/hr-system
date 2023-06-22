package ru.hackathon.sovcombankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        // Отркываю доступ к свагеру всем!
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**" ).permitAll()
                        // Доступ к контроллеру User
                        .requestMatchers("/api/user/registrationUser", "/api/user/registrationHR").permitAll()

                        // For Response
                        .requestMatchers(
                                "/api/response/countActiveResponses",
                                "/api/response/responseSpecification").hasRole("HR")
                        .requestMatchers(
                                "/api/response/countActiveResponses").hasRole("USER")
                        .requestMatchers(
                                "/api/response/getResponseStatus").hasAnyRole("HR", "USER")
                        // For Vacancy
                        .requestMatchers(
                                "/api/vacancy/createVacancy",
                                "/api/vacancy/allVacanciesForHR",
                                "/api/vacancy/getResponsesByVacancy",
                                "/api/vacancy/getCandidatesByVacancy",
                                "/api/vacancy/updateVacancyStatus",
                                "/api/vacancy/updateVacancyInfo",
                                "/api/vacancy/deleteStageInVacancy",
                                "/api/vacancy/countActiveVacancies").hasRole("HR")

                        .requestMatchers(
                                "/api/vacancy/allVacanciesForUser").hasRole("USER")

                        .requestMatchers(
                                "/api/vacancy/getVacancyInfo",
                                "/api/vacancy/getVacancyStages",
                                "/api/vacancy/vacancySpecification").hasAnyRole("USER", "HR")
                        // For User
                        .requestMatchers(
                                "/api/userInfo/userSpecification",
                                "/api/userInfo/allUsers").hasRole("HR")

                        .requestMatchers(
                                "/api/userInfo/createResponseForUser",
                                "/api/userInfo/getUsersResponses",
                                "/api/userInfo/getUsersChallenges").hasRole("User")

                        .requestMatchers(
                                "/api/userInfo/changePhoneNumber",
                                "/api/userInfo/changeEmail",
                                "/api/userInfo/getUsersInfo",
                                "/api/userInfo/getUserInformation").hasAnyRole("USER", "HR")
                        // For StageResult
                        .requestMatchers(
                                "/api/stageResult/setInterviewResult",
                                "/api/stageResult/stageResultSpecification").hasRole("HR")
                        .requestMatchers(
                                "/api/stageResult/saveUserAnswersToStage").hasRole("USER")
                        .requestMatchers(
                                "/api/stageResult/getTestResult").hasAnyRole("USER", "HR")
                        // For Stage
                        .requestMatchers(
                                "/api/stage/createTestStageInVacancy",
                                "/api/stage/createInterviewStageInVacancy",
                                "/api/stage/addTask/open",
                                "/api/stage/addTask/close",
                                "/api/stage/stageSpecification").hasRole("HR")
                        .requestMatchers(
                                "/api/stage/getQuestionsForCertainStage",
                                "/api/stage/getStageById").hasAnyRole("USER", "HR")
                        // For
                        .anyRequest().permitAll()

                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**").allowedOrigins("*");
            }
        };
    }



}