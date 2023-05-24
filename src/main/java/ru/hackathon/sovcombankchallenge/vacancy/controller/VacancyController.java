package ru.hackathon.sovcombankchallenge.vacancy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.vacancy.dto.CreateVacancyDto;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    @Operation(summary = "Create vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Vacancy created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/createVacancy")
    public ResponseEntity<?> createVacancy(@RequestBody CreateVacancyDto dto) {
        return null;
    }

    @Operation(summary = "get all vacancies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found"
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation = Vacancy.class)))
//                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/allVacancies")
    public ResponseEntity<?> getAllVacancies(){ //или мб Vacancy передавать(?)
        return null;
    }

    @Operation(summary = "get responses from certain vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Responses were found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getResponsesByVacancy")
    public ResponseEntity<?> getResponsesByVacancy(@RequestParam Long vacancyId){ //или мб Vacancy передавать(?)
        return null;
    }

    @Operation(summary = "get vacancies by status")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/filterVacanciesByStatus")
    public ResponseEntity<?> getVacanciesByStatus(@RequestParam String status){
        return null;
    }

    @Operation(summary = "get vacancies by its name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/filterVacanciesByStatus")
    public ResponseEntity<?> getVacanciesByItsName(@RequestParam String name){
        return null;
    }

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
    @PostMapping("/createVacancyStage")
    public ResponseEntity<?> addStageToVacancy(@RequestParam Long stageId69, @RequestParam Long vacancyId){
        return null;
    }

    @Operation(summary = "get vacancies by stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/filterVacanciesByStage")
    public ResponseEntity<?> getVacanciesByStage(@RequestParam String stage){
        return null;
    }

    @Operation(summary = "change stage info in vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Stage was saved"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PutMapping("/changeVacancyStage")
    public ResponseEntity<?> changeVacancyStageInfo(@RequestParam Long stageId, @RequestBody Long vacancyId){
        return null;
    }
}
