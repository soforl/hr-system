package ru.hackathon.sovcombankchallenge.vacancy.controller;

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
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.vacancy.dto.CreateVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.UpdateVacancyStatusDto;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.vacancy.specifications.VacancySpecification;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Operation(summary = "Create vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Vacancy created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Vacancy.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/createVacancy")
    public ResponseEntity<?> createVacancy(@RequestBody CreateVacancyDto dto) {
        return null;
//        return ResponseEntity.status(HttpStatus.OK).body(serv.createVacancy(dto.getName(), dto.getDescription(), dto.getWorkExperience(), dto.getVacancyStatus()));
    }

    @Operation(summary = "get all vacancies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Vacancy.class)))
                    }
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

    @Operation(summary = "get responses for certain vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Responses were found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Response.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getResponsesByVacancy")
    public ResponseEntity<?> getResponsesByVacancy(@RequestParam UUID vacancyId){ //или мб Vacancy передавать(?)
        return null;
    }

    @Operation(summary = "get vacancy by id(when you click on button look at vacancy info)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancy info were found",
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
    @GetMapping("/getVacancyInfo")
    public ResponseEntity<?> getVacancyInfo(@RequestParam UUID vacancyId){
        return null;
    }

    @Operation(summary = "get stages for certain vacancy, for example, roadmap")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found",
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
    @GetMapping("/getVacancyStages")
    public ResponseEntity<?> getVacancyStages(@RequestParam UUID vacancyId){
        return null;
    }

    @Operation(summary = "change vacancy status")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "vacancy status was updated",
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
    @PutMapping("/updateVacancyStatus")
    public ResponseEntity<?> updateVacancyStatus(@RequestParam UpdateVacancyStatusDto dto){
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
                                    array = @ArraySchema(schema = @Schema(implementation = Vacancy.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/vacancySpecification")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
            VacancySpecification vacancySpecification = new VacancySpecification();
            searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(vacancySpecification::add);
            List<Vacancy> msGenreList = vacancyRepository.findAll(vacancySpecification);
            return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }
}
