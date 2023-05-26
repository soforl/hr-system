package ru.hackathon.sovcombankchallenge.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.dto.CreateResponseDto;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.user.dto.AddStageToUserDto;
import ru.hackathon.sovcombankchallenge.user.dto.AddUserAnswerDto;
import ru.hackathon.sovcombankchallenge.user.dto.saveUserAnswersToStageDto;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.repository.UserRepository;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import ru.hackathon.sovcombankchallenge.user.specification.UserSpecification;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.specifications.VacancySpecification;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/userInfo")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Operation(summary = "change user's phone number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Phone number changed",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }), // или возвращать информацию только о телефоне?
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changePhoneNumber")
    public ResponseEntity<?> changePhoneNumber(@RequestParam Integer phoneNumber){
        return null;
    }

    @Operation(summary = "change user's email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changeEmail")
    public ResponseEntity<?> changeEmail(@RequestParam String email){
        return null;
    }

    @PostMapping("/createResponseForUser")
    @Operation(summary = "Create new response for user")
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
    public ResponseEntity<?> makeResponse(@RequestBody CreateResponseDto dto){
        return null;
    }

    @GetMapping("/getUsersResponses")
    @Operation(summary = "Get all responses from certain user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
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
    public ResponseEntity<?> getUsersResponses(@RequestParam UUID userId){
        return null;
    }


    @GetMapping("/getUsersInfo")
    @Operation(summary = "Get user's info in some cases =)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> getUsersInfo(@RequestParam UUID userId){
        return null;
    }

    @GetMapping("/getUsersChallenges")
    @Operation(summary = "Here we open page with users tasks or challenges =)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> getUsersChallenges(@RequestParam UUID userId){
        return null;
    }

    @PostMapping("/addAnswerToQuestionInStage")
    @Operation(summary = "Here we add answer to certain question in certain stage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    } // TODO: подумать что здесь надо возвращать... и нужно ли это
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> addAnswerToQuestionInStage(@RequestBody AddUserAnswerDto dto){
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
    public ResponseEntity<?> saveUserAnswersToStage(@RequestBody saveUserAnswersToStageDto dto){
        return null;
    }

    @PostMapping("/getCertainStageForUser")
    @Operation(summary = "Here user get his certain task, for example, when he choose stage and click on it")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    public ResponseEntity<?> getCertainStageForUser(@RequestBody AddStageToUserDto dto){
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
                                    array = @ArraySchema(schema = @Schema(implementation = User.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/userSpecification")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        UserSpecification userSpecification = new UserSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(userSpecification::add);
        List<User> msGenreList = userRepository.findAll(userSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }
}