package ru.hackathon.sovcombankchallenge.login;
import io.jsonwebtoken.*;
import io.github.cdimascio.dotenv.Dotenv;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.user.dto.LoginUserDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.repository.UserRepository;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String secretKey =  System.getProperty("SECRET_KEY", ".env.dev");
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto request) {
        CustomUser user = userService.findUserByUsername(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = generateToken(user.getId());
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String generateToken(UUID userId) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plus(Duration.ofMinutes(30)));

        return Jwts.builder()
                .setIssuer("cheescake-hr-system")
                .setSubject(userId.toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String getSecretKey() {
        Dotenv dotenv = Dotenv.configure().load();
        return dotenv.get("SECRET_KEY");
    }
}
