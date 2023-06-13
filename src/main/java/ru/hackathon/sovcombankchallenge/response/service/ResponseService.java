package ru.hackathon.sovcombankchallenge.response.service;

import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.List;
import java.util.UUID;

public interface ResponseService {
    void create(UUID candidateId, UUID vacancyId);
    List<Response> getAll();
    Response getById(UUID responseId);
    List<StageResult> getResults(UUID responseId);

    List<StageDtoForUser> convertToDtoTest(List<TestStage> stages);

    List<StageDtoForUser> convertToDtoInterview(List<InterviewResult> interviewResults);

    void addStageResult(UUID responseId, UUID stageResultId);
}
