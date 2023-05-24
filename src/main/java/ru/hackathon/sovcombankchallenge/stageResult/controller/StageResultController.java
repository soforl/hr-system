package ru.hackathon.sovcombankchallenge.stageResult.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.stageResult.dto.CreateStageResultDto;

@RestController
@RequestMapping("/api/stageResult")
public class StageResultController {

    @Operation(summary = "create result for the stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was added"
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
                    description = "Result was found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/getTestResult")
    public ResponseEntity<?> getTestResult(@RequestParam Long responseId){
        return null;
    }
}
