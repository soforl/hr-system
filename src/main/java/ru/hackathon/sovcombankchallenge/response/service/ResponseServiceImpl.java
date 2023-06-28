package ru.hackathon.sovcombankchallenge.response.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.stage.enumeration.AccessType;
import ru.hackathon.sovcombankchallenge.stage.models.Interview;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.StageWithAccess;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stage.repository.StageWithAccessRepository;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
import ru.hackathon.sovcombankchallenge.stageResult.service.StageResultService;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
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
    private final StageWithAccessRepository stageWithAccessRepository;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final StageResultService stageResultService;
    private final StageService stageService;

    @Override
    public void create(UUID candidateId, UUID vacancyId) throws Exception {
        if (responseRepository.findByVacancyAndCandidate(vacancyService.getById(vacancyId), userService.getById(candidateId)) != null){
            throw new Exception("you already responded to this vacancy!");
        }

        CustomUser candidate = userService.getById(candidateId);
        Vacancy vacancy = vacancyService.getById(vacancyId);
        Response response = new Response(candidate, vacancy);
        List<Response> vacResponses = vacancy.getResponses();
        vacResponses.add(response);
        responseRepository.save(response);
        vacancy.setResponses(vacResponses);
        vacancyService.save(vacancy);
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

    private StageDtoForUser convertToDtoTest(TestStage stage) {
        var result = StageDtoForUser.builder()
                .id(stage.getId())
                .name(stage.getName())
                .deadline(stage.getDeadline())
                .duration(stage.getDuration())
                .type(stage.getType())
                .build();
        return result;
    }

    @Override
    public List<StageDtoForUser> convertToStageDto(List<Stage> stages){
        var res = new ArrayList<StageDtoForUser>();
        for (var stage: stages) {
            if (stage instanceof TestStage)
                res.add(convertToDtoTest((TestStage) stage));
            else if (stage instanceof Interview) {
                res.add(convertToDtoInterview((Interview) stage));
            }
        }
        return res;
    }

    private StageDtoForUser convertToDtoInterview(Interview stage) {
        var result = StageDtoForUser.builder()
                .id(stage.getId())
                .name("Собеседование")
                .comments(stage.getComments())
                .type(stage.getType())
                .build();
        return result;
    }

    @Override
    public void addStageResult(UUID responseId, UUID stageResultId) {
        Response response = this.getById(responseId);
        StageResult stageResult = stageResultService.getById(stageResultId);
        var results = response.getStageResults();
        results.add(stageResult);
        response.setStageResults(results);
        responseRepository.save(response);
    }
    @Override
    public void openAccess(UUID stageId, UUID responseId) {
        Stage stage = stageService.getById(stageId);
        Response response = this.getById(responseId);
        StageWithAccess access = (StageWithAccess) response.getAccess().stream().filter(item ->
                item.getStage().equals(stage));
        access.setAccess(AccessType.Opened);
        stageWithAccessRepository.save(access);
    }

    @Override
    public void updateStatus(UUID responseId, ResponseStatus status) {
        Response response = this.getById(responseId);
        response.setResponseStatus(status);
        responseRepository.save(response);
    }
}
