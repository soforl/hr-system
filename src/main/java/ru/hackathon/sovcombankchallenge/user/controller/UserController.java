package ru.hackathon.sovcombankchallenge.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.dto.CreateResponseDto;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@RestController
@RequestMapping("/api/userInfo")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "change user's phone number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Phone number changed"
                    ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changePhoneNumber")
    public ResponseEntity<?> changePhoneNumber(@RequestParam Integer phoneNumber){
        return null;
    }

    @Operation(summary = "change user's email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changeEmail")
    public ResponseEntity<?> changeEmail(@RequestParam String email){
        return null;
    }

    @PostMapping("/createResponse")
    @Operation(summary = "Create new response")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> makeResponse(@RequestBody CreateResponseDto dto){
        return null;
    }
}