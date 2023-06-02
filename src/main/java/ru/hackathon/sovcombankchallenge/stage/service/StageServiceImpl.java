package ru.hackathon.sovcombankchallenge.stage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.Interview;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stage.repository.StageRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{

    private final StageRepository stageRepository;
    private final QuestionService questionService;

    @Override
    public Stage createTestStage(String name, LocalDateTime deadline, Long durationSec) {
        var duration = Duration.ofSeconds(durationSec);
        TestStage testStage = new TestStage(name, deadline, duration);
        return stageRepository.save(testStage);
    }

    @Override
    public Stage createInterview(String name, String comments) {
        Interview interview = new Interview(name, comments);
        return stageRepository.save(interview);
    }

    @Override
    public List<Question> getQuestions(UUID stageId) {
        TestStage stage = (TestStage) this.getById(stageId);
        return stage.getQuestions();
    }

    @Override
    public void addQuestion(UUID stageId, UUID questionId) {
        Question question = questionService.getById(questionId);
        TestStage stage = (TestStage) this.getById(stageId);
        List<Question> questions = stage.getQuestions();
        questions.add(question);
        stage.setQuestions(questions);
        stageRepository.save(stage);
    }

    @Override
    public Stage getById(UUID stageId) {
        Optional<Stage> stage = stageRepository.findById(stageId);
        return stage.get();
    }

//    public void saveStageResults(UUID stageId, List<Answer> answers, UUID responseId){
//        stageRepository.findById(stageId)
//    }
}
