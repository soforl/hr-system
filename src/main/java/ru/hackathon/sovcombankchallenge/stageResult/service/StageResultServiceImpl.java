package ru.hackathon.sovcombankchallenge.stageResult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.*;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
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


    @Override
    public void createTestStageResult(Question question, String answer) {
        TestStageResult result = new TestStageResult(question, answer);
        stageResultRepository.save(result);
    }

    @Override
    public StageResult createInterviewResult(String summary, LocalDate date, String linkToZoom) {
        InterviewResult interviewResult = new InterviewResult(summary, date, linkToZoom);
        return stageResultRepository.save(interviewResult);
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
        if (result instanceof TestStageResult) {
            dto = StageResultDto.builder()
                    .stage(result.getStage().getId())
                    .stageName(result.getStage().getName())
                    .user(new UserInfoDto(result.getCandidate().getUsername(),
                                    result.getCandidate().getName(),
                                    result.getCandidate().getPhoneNumber(),
                                    result.getCandidate().getRole(),
                                    result.getCandidate().getImage_url())
                    )
                    .answers(((TestStageResult) result).getAnswers())
                    .points(((TestStageResult) result).getPoints())
                    .build();
        } else if (result instanceof InterviewResult) {
            dto = StageResultDto.builder()
                    .stage(result.getStage().getId())
                    .stageName(result.getStage().getName())
                    .user(new UserInfoDto(result.getCandidate().getUsername(),
                            result.getCandidate().getName(),
                            result.getCandidate().getPhoneNumber(),
                            result.getCandidate().getRole(),
                            result.getCandidate().getImage_url())
                    )
                    .summary(((InterviewResult) result).getSummary())
                    .date(((InterviewResult) result).getDate())
                    .linkToZoom(((InterviewResult) result).getLinkToZoom())
                    .build();
        }
        return dto;
    }
}
