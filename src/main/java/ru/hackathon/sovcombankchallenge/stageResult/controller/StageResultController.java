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
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stageResult.dto.CreateStageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.stageResult.specification.StageResultSpecification;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;
import ru.hackathon.sovcombankchallenge.vacancy.specifications.VacancySpecification;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stageResult")
public class StageResultController {
    @Autowired
    private StageResultRepository stageResultRepository;

    @Operation(summary = "create result for the stage")
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
    @PostMapping("/setResult")
    public ResponseEntity<?> setResult(@RequestBody CreateStageResultDto dto){
        return null;
    }

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
    public ResponseEntity<?> giveResultToUser(@RequestParam UUID stageResultId){
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
