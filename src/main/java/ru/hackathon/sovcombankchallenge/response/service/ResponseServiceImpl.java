package ru.hackathon.sovcombankchallenge.response.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService{
    private final ResponseRepository responseRepository;
    private final StageResultService stageResultService;
    private final UserService userService;
    private final VacancyService vacancyService;

    @Override
    public void create(UUID candidateId, UUID vacancyId) {
        CustomUser candidate = userService.getById(candidateId);
        Vacancy vacancy = vacancyService.getById(vacancyId);
        Response response = new Response(candidate, vacancy);
        responseRepository.save(response);
    }

    @Override
    public List<Response> getAll() {
        List<Response> responses = responseRepository.findAll();
        return responses;
    }

    @Override
    public Response getById(UUID responseId) {
        Optional<Response> response = responseRepository.findById(responseId);
        return response.get();
    }

    @Override
    public List<StageResult> getResults(UUID responseId) {
        Response response = this.getById(responseId);
        return response.getStageResults();
    }


    @Override
    public List<StageDtoForUser> convertToDtoTest(List<TestStage> stages) {
        var result = new ArrayList<StageDtoForUser>();

        for (var stage: stages) {
            result.add(
                    StageDtoForUser.builder()
                            .id(stage.getId())
                            .name(stage.getName())
                            .deadline(stage.getDeadline())
                            .result("to-be-done")
                            .State("to-be-done")
                            .build()
            );
        }
        return result;

    }

    @Override
    public List<StageDtoForUser> convertToDtoInterview(List<InterviewResult> interviewResults) {
        var result = new ArrayList<StageDtoForUser>();
        for (var stage: interviewResults) {
            var result_stage = stage.getSummary().isEmpty() ? "Результатов нет" : "зачтено";
            result.add(
                    StageDtoForUser.builder()
                            .id(stage.getId())
                            .name("Собеседование")
                            .deadline(stage.getDate().atStartOfDay())
                            .additional(stage.getLinkToZoom())
                            .result(result_stage)
                            .State("to-be-done")
                            .build()
            );
        }
        return result;
    }
}
