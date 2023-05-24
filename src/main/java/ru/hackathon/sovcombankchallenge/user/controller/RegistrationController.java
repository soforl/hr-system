package ru.hackathon.sovcombankchallenge.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.user.dto.CreateUser;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

@RestController()
@Tag(name = "User Controller")
@RequestMapping("/api/user")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody CreateUser createUser) {
        User newUser = userService.createUser(createUser.getEmail(), createUser.getPassword(), createUser.getName(),
                createUser.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Hello");
    }

}