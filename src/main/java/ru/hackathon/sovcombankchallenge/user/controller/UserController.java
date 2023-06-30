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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.hackathon.sovcombankchallenge.response.dto.CreateResponseDto;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.specificationInfo.CustomSpecification;
import ru.hackathon.sovcombankchallenge.specificationInfo.SearchCriteria;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.user.dto.ChangeUserInfoDto;
import ru.hackathon.sovcombankchallenge.user.dto.ResponseDto;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.repository.UserRepository;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/userInfo")
//@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ResponseService responseService;

    private final VacancyService vacancyService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, ResponseService responseService, VacancyService vacancyService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.responseService = responseService;
        this.vacancyService = vacancyService;
    }

    @Operation(summary = "change user's phone number - Main page Candidate/HR")
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

    @Operation(summary = "change user's email - Main Page Candidate/HR")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email changed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
                    )
    })
    @PatchMapping("/changeEmail")
//    @PreAuthorize("hasAnyRole('HR', 'USER')")
    public ResponseEntity<?> changeEmail(@RequestBody ChangeUserInfoDto dto){
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
    @Operation(summary = "Get all responses from certain user - K2 page with tasks - 2 methods in one")
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
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsersResponses(@RequestParam UUID userId){
        var user = userService.getById(userId);
        List<Response> userResponses = new ArrayList<>();
        List<ResponseDto> dtos = new ArrayList<>();
        for (Response response: responseService.getAll()) {
            if (response.getCandidate().getId().equals(userId) && response.getVacancy().getVacancyStatus().equals(VacancyStatus.Opened)){
                userResponses.add(response);
            }
        }


        for (Response response: userResponses){

            var stages = response.getVacancy().getStages();
            var stageDtoForUsers = responseService.convertToStageDto(stages);

            dtos.add(ResponseDto.builder()
                            .responseStatus(response.getResponseStatus())
                                    .creationDate(response.getCreationDate())
                                            .stages(stageDtoForUsers)
                                                    .vacancyId(response.getVacancy().getId())
                                                            .vacancyName(response.getVacancy().getName())
                                                                    .build()
                    );
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
        UserInfoDto dto = new UserInfoDto(customUser.getUsername(),
                customUser.getName(),
                customUser.getPhoneNumber(),
                customUser.getImage_url(),
                customUser.getRole().getAuthority(),
                userId);
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
                                    schema = @Schema(implementation = StageDtoForUser.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsersChallenges(@RequestParam UUID userId, @RequestParam UUID vacancyID){
        List<Stage> userStages = new ArrayList<>();
        var vacancy = vacancyService.getById(vacancyID);

        var stages = vacancy.getStages();

        var result = responseService.convertToStageDto(stages);

        // получить отклик
        // получить результаты
        // найти интервью
        // преобразовать в дто


        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @Operation(summary = "if u use enum, then use LIKE or it won't work =) ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Result was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserInfoDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/userSpecification")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userSpecification(@RequestBody List<SearchCriteria> searchCriteria) {
        CustomSpecification<CustomUser> userCustomSpecification = new CustomSpecification<>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(userCustomSpecification::add);
        List<CustomUser> msGenreList = userRepository.findAll(userCustomSpecification);
        var result = msGenreList.stream().map(user -> new UserInfoDto(user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getImage_url(),
                        user.getRole().getAuthority(),
                        user.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
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
        var userInfo = new UserInfoDto(user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getImage_url(),
                user.getRole().getAuthority(),
                user.getId());
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
        var users = userService.getAll().stream().map(user -> new UserInfoDto(user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getImage_url(),
                        user.getRole().getAuthority(),
                        user.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


}