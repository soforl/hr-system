package ru.hackathon.sovcombankchallenge.stageResult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
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
}
