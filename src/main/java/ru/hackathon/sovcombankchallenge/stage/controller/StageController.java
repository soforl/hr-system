package ru.hackathon.sovcombankchallenge.stage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.stage.task.dto.*;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@RestController
@RequestMapping("/api/stage")
public class StageController {
    @Operation(summary = "add new stage to vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Stage was created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/createNewStageInVacancy")
    public ResponseEntity<?> addNewStageToVacancy(@RequestBody CreateStageDto dto){
        return null;
    }

    @Operation(summary = "add existing stage to vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Stage was created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/createNewStageInVacancy")
    public ResponseEntity<?> addStageToVacancy(@RequestBody AddStageToVacancyDto dto){
        return null;
    }


    @Operation(summary = "choose type of task(open, close, etc)")
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
    @PostMapping("/chooseTaskType")
    public ResponseEntity<?> chooseTaskType(@RequestBody String typeOfTask){
        return null;
    }

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
    public ResponseEntity<?> addQuestion(@RequestBody String question){
        return null;
    }

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
    public ResponseEntity<?> addCloseAnswers(@RequestBody String answer){
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
    @PostMapping("/setTimeToStage")
    public ResponseEntity<?> setTestingTime(@RequestBody SetTimeToStageDto dto){
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
    @PostMapping("/setDayToStage")
    public ResponseEntity<?> setFinalPassingDate(@RequestBody SetDateToStageDto dto){
        return null;
    }

    @Operation(summary = "get Stage by id, in some cases, for example, to get all tasks from stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage was found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getStageById")
    public ResponseEntity<?> getStageById(@RequestBody Long stageId){
        return null;
    }

    @Operation(summary = "save Stage, when hr added all questions to it and click on button- finish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage is saved"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PutMapping("/saveStageChanges")
    public ResponseEntity<?> saveStageChanges(@RequestBody Long stageId){
        return null;
    }
}
