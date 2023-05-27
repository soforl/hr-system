package ru.hackathon.sovcombankchallenge.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.user.dto.CreateUser;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

@RestController()
@Tag(name = "User Controller")
@RequestMapping("/api/user")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrationUser")
    @Operation(summary = "Registration user")
    public ResponseEntity<?> registrationUSER(@RequestBody CreateUser createUser) {
        CustomUser newCustomUser = userService.createUser(createUser.getEmail(), createUser.getPassword(), createUser.getName(),
                createUser.getPhoneNumber(), "USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomUser);
    }

    @PostMapping("/registrationHR")
    @Operation(summary = "Registration HR")
    public ResponseEntity<?> registrationHR(@RequestBody CreateUser createUser) {
        CustomUser newCustomUser = userService.createUser(createUser.getEmail(), createUser.getPassword(), createUser.getName(),
                createUser.getPhoneNumber(), "HR");
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomUser);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Hello");
    }
}