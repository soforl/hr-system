package ru.hackathon.sovcombankchallenge.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

@RestController
@RequestMapping("/api/hr")
public class HRController { // не должен ли hr быть завязан на вакансии?
    private final UserService userService;

    public HRController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "get Users by name for search")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users are found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/usersByName")
    public ResponseEntity<?> getUsersByName(@RequestParam String name){
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

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = User.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.allUsers());
    }

    @Operation(summary = "Add stage to user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage was added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/addStageToUser")
    public ResponseEntity<?> addStageToUser(@RequestParam Long stageId,@RequestParam Long userId){
        return null;
    }
}
