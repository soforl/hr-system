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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.repository.StageRepository;
import ru.hackathon.sovcombankchallenge.stage.service.QuestionService;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stage.specification.StageSpecification;
import ru.hackathon.sovcombankchallenge.stage.task.dto.*;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stage")
public class StageController {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageService stageService;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private QuestionService questionService;
    @Operation(summary = "add test stage to vacancy")
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
    @PostMapping("/createTestStageInVacancy")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> addTestStageToVacancy(@RequestBody CreateTestStageDto dto){
        Stage stage = stageService.createTestStage(dto.getStageName(), dto.getDeadline(), dto.getDuration_sec());
        vacancyService.addStage(dto.getVacancyId(), stage.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.getById(dto.getVacancyId()));
    }

    @Operation(summary = "add interview stage to vacancy")
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
    @PostMapping("/createInterviewStageInVacancy")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> addInterviewStageToVacancy(@RequestBody CreateInterviewStageDto dto){
        Stage stage = stageService.createInterview(dto.getStageName(), dto.getComments());
        vacancyService.addStage(dto.getVacancyId(), stage.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.getById(dto.getVacancyId()));
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
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> addStageToVacancy(@RequestBody AddStageToVacancyDto dto){
        vacancyService.addStage(dto.getVacancyId(), dto.getStageId()); // TODO: check if stage exists
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.getById(dto.getVacancyId()));
    }

    @Operation(summary = "add open task to stage")
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
    @PostMapping("/addTask/open")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> addQuestion(@RequestBody CreateOpenQuestionDto dto){
        try {
            Question question = questionService.createOpenQuestion(dto.getQuestion());
            stageService.addQuestion(dto.getStageId(),question.getId());
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "add close task to stage")
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
    @PostMapping("/addTask/close")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> addCloseTask(@RequestBody CreateCloseTaskDto dto){
        try {
            Question question = questionService.createCloseQuestion(dto.getQuestion(),
                    dto.getVar1(), dto.getVar2(),
                    dto.getVar3(), dto.getVar4(),
                    dto.getRightChoose());
            stageService.addQuestion(dto.getStageId(),question.getId());
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
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
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> getStageById(@RequestParam UUID stageId){
        return ResponseEntity.status(HttpStatus.OK).body(stageService.getById(stageId));
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
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        StageSpecification stageSpecification = new StageSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageSpecification::add);
        List<Stage> msGenreList = stageRepository.findAll(stageSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }
}
