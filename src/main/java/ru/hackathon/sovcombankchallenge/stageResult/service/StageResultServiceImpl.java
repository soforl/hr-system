package ru.hackathon.sovcombankchallenge.stageResult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StageResultServiceImpl implements StageResultService{

    private final StageResultRepository stageResultRepository;
    private final StageService stageService;
    private final UserService userService;


    @Override
    public void createTestStageResult(UUID stageId, UUID userId, String answers) {
        Stage stage = stageService.getById(stageId);
        CustomUser user = userService.getById(userId);
        TestStageResult result = new TestStageResult(stage, user, answers);
        stageResultRepository.save(result);
    }

    @Override
    public StageResult createInterviewResult(UUID stageId, String summary, LocalDate date, String linkToZoom) {
        Stage stage = stageService.getById(stageId);
        InterviewResult interviewResult = new InterviewResult(stage, summary, date, linkToZoom);
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
}
