package ru.hackathon.sovcombankchallenge.user.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.dto.CreateResponseDto;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.user.dto.ChangeUserInfoDto;
import ru.hackathon.sovcombankchallenge.user.dto.ResponseDto;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.repository.UserRepository;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.user.specification.UserSpecification;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/userInfo")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private StageResultService stageResultService;
    @Autowired
    private VacancyService vacancyService;

    @Operation(summary = "change user's phone number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Phone number changed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changePhoneNumber")
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> changePhoneNumber(@RequestBody ChangeUserInfoDto dto){
        try{
            userService.updateUserPhoneNumber(dto.getUserId(), dto.getPhoneOrEmail());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "change user's email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changeEmail")
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> changeEmail(@RequestParam ChangeUserInfoDto dto){
        try {
            userService.updateUserEmailNumber(dto.getUserId(), dto.getPhoneOrEmail());
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createResponseForUser")
    @Operation(summary = "Create new response for user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response was created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> makeResponse(@RequestBody CreateResponseDto dto){
        try {
            responseService.create(dto.getUser(), dto.getVacancy());
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsersResponses(@RequestParam UUID userId){
        List<Response> userResponses = new ArrayList<>();
        List<ResponseDto> dtos = new ArrayList<>();
        for (Response response: responseService.getAll()) {
            if (response.getCandidate() == userService.getById(userId)){
                userResponses.add(response);
            }
        }
        for (Response response: userResponses){
            dtos.add(new ResponseDto(response.getResponseStatus(),
                    response.getCreationDate(),
                    response.getVacancy().getName(),
                    response.getVacancy().convertToDto()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
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
                                    schema = @Schema(implementation = UserInfoDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsersInfo(@RequestParam UUID userId){
        CustomUser customUser = userService.getById(userId);
        UserInfoDto dto = new UserInfoDto(customUser.getUsername(), customUser.getName(), customUser.getPhoneNumber(), customUser.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
                                    array = @ArraySchema(schema = @Schema(implementation = Stage.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsersChallenges(@RequestParam UUID userId){
        List<Stage> userStages = new ArrayList<>();
        for (Response response: responseService.getAll()) {
            if (response.getCandidate() == userService.getById(userId)){
                userStages.addAll(response.getVacancy().getStages());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userStages);
    }


    @Operation(summary = "if u use enum, then use LIKE or it won't work =) ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
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
    @PostMapping("/userSpecification")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> specification(@RequestBody List<SearchCriteria> searchCriteria) {
        UserSpecification userSpecification = new UserSpecification();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(userSpecification::add);
        List<CustomUser> msGenreList = userRepository.findAll(userSpecification);
        return ResponseEntity.status(HttpStatus.OK).body(msGenreList);
    }

    @GetMapping("/getUserInformation")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserInfoDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        var user = userService.findUserByUsername(authentication.getName());
        var userInfo = new UserInfoDto(user.getUsername(), user.getName(), user.getPhoneNumber(), user.getRole());
        return ResponseEntity.ok().body(userInfo);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomUser.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @GetMapping("/allUsers")
//    @PreAuthorize("hasAnyRole('HR')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }


}