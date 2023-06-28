package ru.hackathon.sovcombankchallenge.stage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.tomcat.websocket.MessageHandlerResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.specificationInfo.CustomSpecification;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.*;
import ru.hackathon.sovcombankchallenge.stage.repository.StageRepository;
import ru.hackathon.sovcombankchallenge.stage.service.QuestionService;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stage.task.dto.*;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stage")
public class StageController {
    private final StageRepository stageRepository;
    private final StageService stageService;
    private final VacancyService vacancyService;
    private final QuestionService questionService;

    @Autowired
    public StageController(StageRepository stageRepository, StageService stageService, VacancyService vacancyService, QuestionService questionService) {
        this.stageRepository = stageRepository;
        this.stageService = stageService;
        this.vacancyService = vacancyService;
        this.questionService = questionService;
    }

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
        Stage stage = stageService.createTestStage("Тестирование", dto.getStageType());
        vacancyService.addStage(dto.getVacancyId(), stage.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(stage); // или нужно возвращать инфу про вакансию?
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
        Stage stage = stageService.createInterview(dto.getStageName(), dto.getComments(), dto.getStageType());
        vacancyService.addStage(dto.getVacancyId(), stage.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(stage);
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
    public ResponseEntity<?> addOpenQuestion(@RequestBody CreateOpenQuestionDto dto){
        Question question = questionService.createOpenQuestion(dto.getQuestion());
        stageService.addQuestion(dto.getStageId(),question.getId());
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
        catch (NullPointerException e) {
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
        Stage stage = stageService.getById(stageId);
        ReturnStageDto dto = stageService.convertToStageDto(stage);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    } // todo throw exception
    // TODO: make method save all


    @Operation(summary = "get questions for certain stage- page test")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage result was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = QuestionDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getQuestionsForCertainStage")
    public ResponseEntity<?> getQuestionsForCertainStage(@RequestParam UUID stageId){
        Stage stage = stageService.getById(stageId);
        ReturnStageDto dto = stageService.convertToStageDto(stage);
        return ResponseEntity.status(HttpStatus.OK).body(dto.getQuestions());
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
        CustomSpecification<Stage> stageSpecification = new CustomSpecification<>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageSpecification::add);
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageSpecification::add);
        List<Stage> msGenreList = stageRepository.findAll(stageSpecification);
        var result = msGenreList.stream().map(stageService::convertToStageDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/saveAllTestInfo")
    public ResponseEntity<?> saveAllTestInfo(@RequestBody StageDto dto){
        stageService.saveTestInfo(dto.getStageId(), dto.getDeadline(), dto.getDuration_sec(), dto.getStageName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteQuestionFromStage")
    public ResponseEntity<?> deleteQuestion(@RequestBody DeleteQuestionDto dto){
        stageService.deleteQuestionFromStage(dto.getQuestionId(), dto.getStageId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Operation(summary = "get questions for certain stage for user")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Stage result was created",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation = QuestionDto.class)))
//                    }
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad Request"
//            )
//    })
//    @GetMapping("/getQuestionsForCertainStage")
//    public ResponseEntity<?> getQuestionsForCertainStageForUser(@RequestParam UUID stageId){
//        Stage stage = stageService.getById(stageId);
//        ReturnStageDto dto = stageService.convertToStageDto(stage);
//        return ResponseEntity.status(HttpStatus.OK).body(dto.getQuestions());
//    }
}
