package ru.hackathon.sovcombankchallenge.response.controller;

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
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.CustomSpecification;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.dto.ResponseDto;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.dto.CountDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/response")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private UserService userService;

    @Operation(summary = "if u use enum, then use LIKE or it won't work =) ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Responses were found by filters",
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
    @PostMapping("/responseSpecification")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        CustomSpecification<Response> responseSpecification = new CustomSpecification<>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(responseSpecification::add);
        List<Response> msGenreList = responseRepository.findAll(responseSpecification);
        var result = msGenreList.stream().map(resp -> ResponseDto.builder()
                .vacancyId(resp.getVacancy().getId())
                .vacancyName(resp.getVacancy().getName())
                .responseStatus(resp.getResponseStatus())
                .user(new UserInfoDto(resp.getCandidate().getUsername(),
                                resp.getCandidate().getName(),
                                resp.getCandidate().getPhoneNumber(),
                                resp.getCandidate().getImage_url(),
                                resp.getCandidate().getRole().getAuthority(),
                                resp.getCandidate().getId())
                )
                .creationDate(resp.getCreationDate())
                .stages(responseService.convertToStageDto(resp.getAccessStages()))
//                .results() // todo: может добавить? убрать стейдж и добавить резалтс
                .build());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "get Status response")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response status was found",
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
    @GetMapping("/getResponseStatus")
//    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<?> getResponseStatus(@RequestBody UUID responseId){
        return ResponseEntity.status(HttpStatus.OK).body(responseService.getById(responseId).getResponseStatus());
    }

    @Operation(summary = "counting active responses")
    @GetMapping("/countActiveResponses")
    public ResponseEntity<?> countActiveResponses(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CountDto(responseService.getAll().stream()
                        .filter(resp -> resp.getResponseStatus().equals(ResponseStatus.UnderConsideration))
                        .count()));
    }

    @Operation(summary = "counting active responses for certain vacancy")
    @GetMapping("/countAllResponsesForVacancy")
//    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<?> countAllResponsesForVacancy(@RequestParam UUID vacancyId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CountDto(responseService.getAll().stream()
                        .filter(response -> response.getVacancy().getId().equals(vacancyId))
                        .filter(resp -> resp.getResponseStatus().equals(ResponseStatus.UnderConsideration) ||
                                resp.getResponseStatus().equals(ResponseStatus.Closed))
                        .count()));
    }
}
