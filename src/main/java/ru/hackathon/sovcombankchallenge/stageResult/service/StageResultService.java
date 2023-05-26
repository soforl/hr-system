package ru.hackathon.sovcombankchallenge.stageResult.service;

import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;

import java.time.LocalDate;
import java.util.UUID;

public interface StageResultService {
    void createTestStageResult(Question question, String answer);
    void createInterviewResult(String summary, LocalDate date, String linkToZoom);
    StageResult getById(UUID resultId);
}
