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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.specificationInfo.CustomSpecification;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.dto.CreateVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.UpdateVacancyStatusDto;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;
//import ru.hackathon.sovcombankchallenge.vacancy.specifications.VacancySpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private VacancyService vacancyService;

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
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> createVacancy(@RequestBody CreateVacancyDto dto) {
        try {
            vacancyService.create(dto.getName(), dto.getDescription(), dto.getVacancyStatus(), dto.getWorkExperience(),
                    dto.getSphereType());
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all vacancies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacancies were found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ReturnVacancyDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/allVacancies")
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> getAllVacancies(){
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.convertToDtoVacancy(vacancyService.getAll()));
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
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> getResponsesByVacancy(@RequestParam UUID vacancyId){
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.getResponsesByVacancy(vacancyId));
    }

    @Operation(summary = "get candidates for certain vacancy")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Candidates were found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomUser.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/getCandidatesByVacancy")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> getCandidatesByVacancy(@RequestParam UUID vacancyId){
        List<CustomUser> candidates = new ArrayList<>();
        for (Response rep: vacancyService.getResponsesByVacancy(vacancyId)) {
            candidates.add(rep.getCandidate());
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
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
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> getVacancyInfo(@RequestParam UUID vacancyId){
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.getById(vacancyId));
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
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> getVacancyStages(@RequestParam UUID vacancyId){
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.getStages(vacancyId));
    }

    @Operation(summary = "change vacancy status")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "vacancy status was updated"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PutMapping("/updateVacancyStatus")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> updateVacancyStatus(@RequestParam UpdateVacancyStatusDto dto){
        try{
            vacancyService.updateStatus(dto.getVacancyId(), dto.getVacancyStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        CustomSpecification<Vacancy> vacancyCustomSpecification = new CustomSpecification<>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(vacancyCustomSpecification::add);
        List<Vacancy> msGenreList = vacancyRepository.findAll(vacancyCustomSpecification);

        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.returnSpecDto(msGenreList));
    }
}
