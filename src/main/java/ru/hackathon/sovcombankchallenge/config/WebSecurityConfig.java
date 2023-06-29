package ru.hackathon.sovcombankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
import org.springframework.web.filter.CorsFilter;
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
        http.cors().and().csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        // Отркываю доступ к свагеру всем!
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**" ).permitAll()
                        // Доступ к контроллеру User
                        .requestMatchers("/api/user/registrationUser", "/api/user/registrationHR").permitAll()

                        // HR
                        .requestMatchers(
                                "/api/response/countActiveResponses",
                                "/api/response/responseSpecification",
                                "/api/vacancy/createVacancy",
                                "/api/vacancy/allVacanciesForHR",
                                "/api/vacancy/getResponsesByVacancy",
                                "/api/vacancy/getCandidatesByVacancy",
                                "/api/vacancy/updateVacancyStatus",
                                "/api/vacancy/updateVacancyInfo",
                                "/api/vacancy/deleteStageInVacancy",
                                "/api/vacancy/countActiveVacancies",
                                "/api/userInfo/userSpecification",
                                "/api/userInfo/allUsers",
                                "/api/stageResult/setInterviewResult",
                                "/api/stageResult/stageResultSpecification",
                                "/api/stage/createTestStageInVacancy",
                                "/api/stage/createInterviewStageInVacancy",
                                "/api/stage/addTask/open",
                                "/api/stage/addTask/close",
                                "/api/stage/stageSpecification").hasRole("HR")

                        // User
                        .requestMatchers(
                                "/api/response/countActiveResponses",
                                "/api/vacancy/allVacanciesForUser",
                                "/api/userInfo/createResponseForUser",
                                "/api/userInfo/getUsersResponses",
                                "/api/userInfo/getUsersChallenges",
                                "/api/stageResult/saveUserAnswersToStage").hasRole("USER")

//                        // HR & User
//                        .requestMatchers(
//                                "/api/response/getResponseStatus",
//                                "/api/vacancy/getVacancyInfo",
//                                "/api/vacancy/getVacancyStages",
//                                "/api/vacancy/vacancySpecification",
//                                "/api/userInfo/changePhoneNumber",
//                                "/api/userInfo/changeEmail",
//                                "/api/userInfo/getUsersInfo",
//                                "/api/userInfo/getUserInformation",
//                                "/api/stageResult/getTestResult",
//                                "/api/stage/getQuestionsForCertainStage",
//                                "/api/stage/getStageById").hasAnyRole("USER", "HR")
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
                        .addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }

//    @Bean
//    public CorsFilter corsFilter() {
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        //config.setAllowCredentials(true); // you USUALLY want this
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }



}