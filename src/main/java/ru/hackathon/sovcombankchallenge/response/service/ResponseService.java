package ru.hackathon.sovcombankchallenge.response.service;

import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;

import java.util.List;
import java.util.UUID;

public interface ResponseService {
    void create(UUID candidateId, UUID vacancyId);
    List<Response> getAll();
    Response getById(UUID responseId);
    List<StageResult> getResults(UUID responseId);
}
