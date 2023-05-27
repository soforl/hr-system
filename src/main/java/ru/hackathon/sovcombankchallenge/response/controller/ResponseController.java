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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.response.specification.ResponseSpecification;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;

import java.util.List;

@RestController
@RequestMapping("/api/response")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

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
        ResponseSpecification responseSpecification = new ResponseSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(responseSpecification::add);
        List<Response> msGenreList = responseRepository.findAll(responseSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }
}
