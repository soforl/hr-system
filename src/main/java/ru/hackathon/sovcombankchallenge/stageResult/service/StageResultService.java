package ru.hackathon.sovcombankchallenge.stageResult.service;

import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.UUID;

public interface StageResultService {
    void createTestStageResult(Question question, String answer);
    StageResult createInterviewResult(String summary, LocalDate date, String linkToZoom);
    StageResult getById(UUID resultId);

    StageResult findByVanacyAndStage(Vacancy vacancy, Stage stage);
    StageResultDto convertToStageResultDto(StageResult result);
}
