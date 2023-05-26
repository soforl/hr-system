package ru.hackathon.sovcombankchallenge.stage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.repository.StageRepository;
import ru.hackathon.sovcombankchallenge.stage.specification.StageSpecification;
import ru.hackathon.sovcombankchallenge.stage.task.dto.*;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.stageResult.specification.StageResultSpecification;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stage")
public class StageController {

    @Autowired
    private StageRepository stageRepository;
    @Operation(summary = "add new stage to vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Stage was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Vacancy.class))
                    }
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
                    description = "Stage was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Vacancy.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/addStageToVacancy")
    public ResponseEntity<?> addStageToVacancy(@RequestBody AddStageToVacancyDto dto){
        return null;
    }


    @Operation(summary = "choose type of task(open, close, etc)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task was added to stage",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Question.class))
                    }
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
                    description = "Task was added to stage",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Question.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody String question){
        return null;
    }

    @Operation(summary = "add task to stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task was added to stage",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Question.class))
                    } // TODO: здесь будет answer мб?
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/addCloseAnswer")
    public ResponseEntity<?> addCloseAnswers(@RequestBody String answer){
        return null;
    }

    @Operation(summary = "set time for passing stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Time is set",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Stage.class))
                    }
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
                    description = "Last Day is set",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Stage.class))
                    }
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
                    description = "Stage was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Stage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getStageById")
    public ResponseEntity<?> getStageById(@RequestBody UUID stageId){
        return null;
    }

    @Operation(summary = "save Stage, when hr added all questions to it and click on button- finish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage is saved",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Stage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PutMapping("/saveStageChanges")
    public ResponseEntity<?> saveStageChanges(@RequestBody UUID stageId){
        return null;
    }

    @Operation(summary = "if u use enum, then use LIKE or it won't work =) ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Stage.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/stageSpecification")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        StageSpecification stageSpecification = new StageSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageSpecification::add);
        List<Stage> msGenreList = stageRepository.findAll(stageSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }
}
