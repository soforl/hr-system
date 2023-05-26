package ru.hackathon.sovcombankchallenge.stageResult.service;

import ru.hackathon.sovcombankchallenge.stage.models.Question;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface StageResultService {
    void createTestStageResult(Question question, String answer);
    void createInterviewResult(String summary, LocalDate date, String linkToZoom);
}
