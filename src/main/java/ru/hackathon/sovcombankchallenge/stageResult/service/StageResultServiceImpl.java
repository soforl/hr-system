package ru.hackathon.sovcombankchallenge.stageResult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.stage.models.*;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StageResultServiceImpl implements StageResultService{

    private final StageResultRepository stageResultRepository;
    private final StageService stageService;
    private final UserService userService;
    private final ResponseService responseService;


    @Override
    public void createTestStageResult(UUID stageId, UUID userId, List<String> answers, UUID responseId) {
        Stage stage = stageService.getById(stageId);
        CustomUser user = userService.getById(userId);
        TestStageResult result = new TestStageResult(stage, user, answers);
        StageResult res = stageResultRepository.save(result);
        responseService.addStageResult(responseId, res);
    }

    @Override
    public void createInterviewResult(UUID stageId, UUID userId, String summary, LocalDate date, String linkToZoom, UUID responseId) {
        Stage stage = stageService.getById(stageId);
        CustomUser user = userService.getById(userId);
        InterviewResult interviewResult = new InterviewResult(stage, user, summary, date, linkToZoom);
        StageResult result = stageResultRepository.save(interviewResult);
        responseService.addStageResult(responseId, result);
    }

    @Override
    public StageResult getById(UUID resultId) {
        Optional<StageResult> result = stageResultRepository.findById(resultId);
        return result.get();
    }

    @Override
    public StageResult findByVanacyAndStage(Vacancy vacancy, Stage stage) {
        return null;
    }

    @Override
    public StageResultDto convertToStageResultDto(StageResult result) {
        StageResultDto dto = null;
        if (result instanceof TestStageResult && result.getStage() instanceof TestStage) {
            dto = StageResultDto.builder()
                    .stage(result.getStage().getId())
                    .stageName(result.getStage().getName())
                    .user(new UserInfoDto(result.getCandidate().getUsername(),
                                    result.getCandidate().getName(),
                                    result.getCandidate().getPhoneNumber(),
                                    result.getCandidate().getImage_url(),
                                    result.getCandidate().getRole().getAuthority(),
                                    result.getCandidate().getId())
                    )
                    .answers(((TestStageResult) result).getAnswers())
                    .points(((TestStageResult) result).getPoints())
                    .questions(((TestStage) result.getStage()).getQuestions().stream().map(Question::getText).toList())
                    .build();
        } else if (result instanceof InterviewResult) {
            dto = StageResultDto.builder()
                    .stage(result.getStage().getId())
                    .stageName(result.getStage().getName())
                    .user(new UserInfoDto(result.getCandidate().getUsername(),
                            result.getCandidate().getName(),
                            result.getCandidate().getPhoneNumber(),
                            result.getCandidate().getImage_url(),
                            result.getCandidate().getRole().getAuthority(),
                            result.getCandidate().getId())
                    )
                    .summary(((InterviewResult) result).getSummary())
                    .date(((InterviewResult) result).getDate())
                    .linkToZoom(((InterviewResult) result).getLinkToZoom())
                    .build();
        }
        return dto;
    }
}
