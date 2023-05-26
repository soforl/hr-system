package ru.hackathon.sovcombankchallenge.stage.service;

import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface StageService {
    void createTestStage(String name, LocalDateTime deadline, Duration duration);
    void createInterview(String name, String comments);
    void addQuestion(UUID stageId, UUID questionId);
    List<Question> getQuestions(UUID stageId);
    Stage getById(UUID stageId);
}
