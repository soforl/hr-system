package ru.hackathon.sovcombankchallenge.stage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.*;
import ru.hackathon.sovcombankchallenge.stage.repository.StageRepository;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public ReturnStageDto convertToStageDto(Stage stage) {
        ReturnStageDto dto = null;
        if (stage instanceof TestStage) {
            var questions = new ArrayList<QuestionDto>();
            ((TestStage) stage).getQuestions()
                    .forEach(q -> {
                        if (q instanceof OpenQuestion)
                            questions.add(QuestionDto.builder()
                                    .question(q.getText())
                                    .id(q.getId())
                                    .type("Open")
                                    .build());
                        else if (q instanceof CloseQuestion)
                            questions.add(QuestionDto.builder()
                                    .question(q.getText())
                                    .id(q.getId())
                                    .type("Close")
                                    .var1(((CloseQuestion) q).getVar1())
                                    .var2(((CloseQuestion) q).getVar2())
                                    .var3(((CloseQuestion) q).getVar3())
                                    .var4(((CloseQuestion) q).getVar4())
                                    .rightChoose(((CloseQuestion) q).getRightChoose())
                                    .build());
                    });
            dto = ReturnStageDto.builder()
                    .id(stage.getId())
                    .stageName(stage.getName())
                    .deadline(((TestStage) stage).getDeadline())
                    .duration(((TestStage) stage).getDuration().toSeconds())
                    .questions(questions)
                    .build();
        } else if (stage instanceof Interview) {
            dto = ReturnStageDto.builder()
                    .id(stage.getId())
                    .stageName(stage.getName())
                    .comments(((Interview) stage).getComments())
                    .build();
        }
        return dto;
    }

//    public void saveStageResults(UUID stageId, List<Answer> answers, UUID responseId){
//        stageRepository.findById(stageId)
//    }
}
