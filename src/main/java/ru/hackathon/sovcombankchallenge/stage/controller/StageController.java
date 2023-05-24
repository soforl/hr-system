package ru.hackathon.sovcombankchallenge.stage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.stage.task.dto.CreateTaskDto;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

import java.time.LocalDateTime;
import java.time.Period;

@RestController
@RequestMapping("/api/stage")
public class StageController {

//    public ResponseEntity<?> makeTestStage(@RequestParam String info){//или сущность передавать
//        return null;
//    }
//
//    public ResponseEntity<?> makeInterviewStage(@RequestParam String info){
//        return null;
//    }
//
//    public ResponseEntity<?> addExsistedStage(){
//        return null;
//    }

    @Operation(summary = "add task to stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task was added to stage"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody CreateTaskDto dto){
        return null;
    }

    @Operation(summary = "set time for passing stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Time is set"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/setTime")
    public ResponseEntity<?> setTestingTime(@RequestBody Period period){
        return null;
    }

    @Operation(summary = "set last day for passing stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Last Day is set"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/setDay")
    public ResponseEntity<?> setFinalPassingDate(@RequestBody LocalDateTime time){
        return null;
    }


}
