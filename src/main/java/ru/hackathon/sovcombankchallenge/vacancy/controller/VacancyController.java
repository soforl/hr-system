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
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.CustomSpecification;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.dto.CreateVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.UpdateVacancyInfoDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.UpdateVacancyStatusDto;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
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
    private final VacancyRepository vacancyRepository;
    private final VacancyService vacancyService;
    private final ResponseService responseService;

    @Autowired
    public VacancyController(VacancyRepository vacancyRepository, VacancyService vacancyService, ResponseService responseService) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyService = vacancyService;
        this.responseService = responseService;
    }

    @Operation(summary = "Create vacancy = save vacancy - page where vacancy appears")
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
            vacancyService.create(dto.getName(), dto.getDescription(), dto.getVacancyStatus(), dto.getWorkExperience(), dto.getSphere());
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all vacancies for HR")
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
    @GetMapping("/allVacanciesForHR")
//    @PreAuthorize("hasAnyRole('HR')")
    public ResponseEntity<?> getAllVacancies(){ // todo: делать метод allVac для user(например, без вак которые в архиве)
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.convertToDtoVacancy(vacancyService.getAll()));
    }

    @Operation(summary = "get all vacancies for user")
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
    @GetMapping("/allVacanciesForUser")
    public ResponseEntity<?> getAllVacanciesForUser(){
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.returnVacForUser(vacancyService.getAll()));
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
        List<UserInfoDto> candidates = new ArrayList<>();
        for (var rep: vacancyService.getResponsesByVacancy(vacancyId)) {
            candidates.add(rep.getUser());
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
        Vacancy vacancy = vacancyService.getById(vacancyId);
        ReturnVacancyDto dto = ReturnVacancyDto.builder()
                .vacancyId(vacancyId)
                .vacancyName(vacancy.getName())
                .description(vacancy.getDescription())
                .vacancyStatus(vacancy.getVacancyStatus())
                .workExperience(vacancy.getWorkExperience())
                .sphere(vacancy.getSphere())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
        var result = responseService.convertToStageDto(vacancyService.getStages(vacancyId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
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
    public ResponseEntity<?> updateVacancyStatus(@RequestBody UpdateVacancyStatusDto dto){
        try{
            vacancyService.updateStatus(dto.getVacancyId(), dto.getVacancyStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "update Vacancy parameters, such as status, name and etc")
    @PutMapping("/updateVacancyInfo")
    public ResponseEntity<?> updateVacancyInfo(@RequestBody UpdateVacancyInfoDto dto){
        if (dto.getName() != null)
            vacancyService.updateName(dto.getVacancyId(), dto.getName());
        if (dto.getDescription() != null)
            vacancyService.updateDescription(dto.getVacancyId(), dto.getDescription());
        if (dto.getVacancyStatus() != null)
            vacancyService.updateStatus(dto.getVacancyId(), dto.getVacancyStatus());
        if (dto.getWorkExperience() != null)
            vacancyService.updateWorkExperience(dto.getVacancyId(), dto.getWorkExperience());
        if (dto.getSphereType() != null)
            vacancyService.updateSphere(dto.getVacancyId(), dto.getSphereType());
        Vacancy vacancy = vacancyService.getById(dto.getVacancyId());
        ReturnVacancyDto vac = ReturnVacancyDto.builder()
                .vacancyId(vacancy.getId())
                .vacancyName(vacancy.getName())
                .description(vacancy.getDescription())
                .vacancyStatus(vacancy.getVacancyStatus())
                .workExperience(vacancy.getWorkExperience())
                .sphere(vacancy.getSphere())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(vac);
    }


    @Operation(summary = "delete stage in vacancy")
    @DeleteMapping("/deleteStageInVacancy")
    public ResponseEntity<?> deleteStageInVacancy(@RequestParam UUID stageId,
                                                  @RequestParam UUID vacancyId){
        vacancyService.removeStage(vacancyId, stageId);
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
        var result = msGenreList.stream().map(vac -> ReturnVacancyDto.builder()
                .vacancyId(vac.getId())
                .vacancyName(vac.getName())
                .description(vac.getDescription())
                .vacancyStatus(vac.getVacancyStatus())
                .workExperience(vac.getWorkExperience())
                .sphere(vac.getSphere())
                .build());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "counting active vacancies")
    @PostMapping("/countActiveVacancies")
    public ResponseEntity<Long> countActiveVacancies(){
        return ResponseEntity.status(HttpStatus.OK)
                        .body(vacancyService.getAll().stream()
                                .filter(vac -> vac.getVacancyStatus().equals(VacancyStatus.Opened))
                                .count());
    }

    @Operation(summary = "delete all vacancies")
    @DeleteMapping("/deleteAllVacancies")
    public ResponseEntity<?> deleteAllVacancies() {
        vacancyService.deleteAllVacancies();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete vacancy")
    @DeleteMapping("/deleteVacancy")
    public ResponseEntity<?> deleteVacancy(@RequestParam UUID vacancyId) {
        vacancyService.deleteVacancy(vacancyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
