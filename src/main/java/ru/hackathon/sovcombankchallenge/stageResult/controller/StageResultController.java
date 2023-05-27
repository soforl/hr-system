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
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stageResult.dto.CreateStageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.dto.ResultToUserDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.stageResult.specification.StageResultSpecification;
import ru.hackathon.sovcombankchallenge.stageResult.dto.SaveUserAnswersToStageDto;
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
    public ResponseEntity<?> setInterviewResult(@RequestBody CreateStageResultDto dto){
        stageResultService.createInterviewResult(dto.getResult(), dto.getDate(), dto.getLinkToZoom());
        return null;
    }

    @PostMapping("/saveUserAnswersToStage")
    @Operation(summary = "Here we save user's answers for whole stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> saveUserAnswersToStage(@RequestBody SaveUserAnswersToStageDto dto){

        return null;
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

    @Operation(summary = "get results")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
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
    @PostMapping("/getTestResult")
    public ResponseEntity<?> getTestResult(@RequestParam UUID responseId){

        return null;
    }

    @Operation(summary = "give to User result of all tests: offer or else")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/giveResultToUser")
    public ResponseEntity<?> giveResultToUser(@RequestBody ResultToUserDto dto){
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
                                    array = @ArraySchema(schema = @Schema(implementation = StageResult.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/stageResultSpecification")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        StageResultSpecification stageResultSpecification = new StageResultSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(stageResultSpecification::add);
        List<StageResult> msGenreList = stageResultRepository.findAll(stageResultSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }


}
