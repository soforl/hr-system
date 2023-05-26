package ru.hackathon.sovcombankchallenge.stage.service;

import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public interface StageService {
    void createTestStage(String name, LocalDateTime deadline, Duration duration);
    void createInterview(String name, String comments);
    void addQuestion(UUID stageId, UUID questionId);
    Stage getById(UUID stageId);
}
