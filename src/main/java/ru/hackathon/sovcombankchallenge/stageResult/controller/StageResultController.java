package ru.hackathon.sovcombankchallenge.stageResult.controller;

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
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stageResult.dto.CreateStageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.dto.ResultToUserDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.stageResult.specification.StageResultSpecification;
import ru.hackathon.sovcombankchallenge.stageResult.dto.SaveUserAnswersToStageDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stageResult")
public class StageResultController {
    @Autowired
    private StageResultRepository stageResultRepository;
    @Autowired
    private StageResultService stageResultService;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private ResponseService responseService;

    @Operation(summary = "create result for interview")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was added",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StageResult.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/setInterviewResult")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> setInterviewResult(@RequestBody CreateStageResultDto dto){
        try{
            StageResult stage = stageResultService.createInterviewResult(null, dto.getDate(), dto.getLinkToZoom());
            responseService.getById(dto.getResponseId()).addStageResult(stage);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @Operation(summary = "create result for test")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Result was added",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = StageResult.class))
//                    }
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad Request"
//            )
//    })
//    @PostMapping("/setTestResult")
//    public ResponseEntity<?> setTestResult(@RequestBody CreateStageResultDto dto){
//        stageResultService.createTestStageResult();
//        return null;
//    }

//    @PostMapping("/saveUserAnswersToStage")
//    @Operation(summary = "Here we save user's answers for whole stage")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Response was created",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = Response.class))
//                    }
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad Request"
//            )
//    })
//    public ResponseEntity<?> saveUserAnswersToStage(@RequestBody SaveUserAnswersToStageDto dto){
//        Response response = responseService.getById(dto.getResponseId());
////        response.
//        return null;
//    }


    @Operation(summary = "get results")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StageResult.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/getTestResult")
    public ResponseEntity<?> getTestResult(@RequestParam UUID responseId){
        return ResponseEntity.status(HttpStatus.OK).body(responseService.getById(responseId).getStageResults());
    }

//    @Operation(summary = "give to User result of all tests: offer or else")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Result was found",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = Response.class))
//                    }
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad Request"
//            )
//    })
//    @PostMapping("/giveInterviewResultToUser")
//    @PreAuthorize("hasRole('HR')")
//    public ResponseEntity<?> giveInterviewResultToUser(@RequestBody ResultToUserDto dto){
//        Response response = responseService.getById(dto.getResponseId());
//        for (StageResult result: response.getStageResults()) {
//            if (result.getId() == dto.getResultId()){
//                result.
//            }
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(responseService.getById(responseId).getStageResults());
//    }

    @Operation(summary = "if u use enum, then use LIKE or it won't work =) ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StageResult.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/stageResultSpecification")
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        StageResultSpecification stageResultSpecification = new StageResultSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageResultSpecification::add);
        List<StageResult> msGenreList = stageResultRepository.findAll(stageResultSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }


}
