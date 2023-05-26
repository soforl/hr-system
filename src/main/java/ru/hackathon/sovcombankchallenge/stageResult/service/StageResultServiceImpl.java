package ru.hackathon.sovcombankchallenge.stageResult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.repository.StageResultRepository;

import java.time.LocalDate;

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
    public void createInterviewResult(String summary, LocalDate date, String linkToZoom) {
        InterviewResult interviewResult = new InterviewResult(summary, date, linkToZoom);
        stageResultRepository.save(interviewResult);
    }
}
