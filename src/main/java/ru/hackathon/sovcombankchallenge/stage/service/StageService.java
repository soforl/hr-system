package ru.hackathon.sovcombankchallenge.stage.service;

import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface StageService {
    Stage createTestStage(String name, LocalDateTime deadline, Long duration);
    Stage createInterview(String name, String comments);
    void addQuestion(UUID stageId, UUID questionId);
    List<Question> getQuestions(UUID stageId);
    Stage getById(UUID stageId);
    ReturnStageDto convertToStageDto(Stage stage);
}
