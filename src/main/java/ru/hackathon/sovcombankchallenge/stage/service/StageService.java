package ru.hackathon.sovcombankchallenge.stage.service;

import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface StageService {
    Stage createTestStage(String name, StageType type);
    Stage createInterview(String name, String comments, StageType type);
    void addQuestion(UUID stageId, UUID questionId);
    List<Question> getQuestions(UUID stageId);
    Stage getById(UUID stageId);
    ReturnStageDto convertToStageDto(Stage stage, String role);
    void deleteQuestionFromStage(UUID question, UUID stage);
    Stage saveTestInfo(UUID stageId, LocalDateTime deadline, Long duration_sec, String stageName);
}
