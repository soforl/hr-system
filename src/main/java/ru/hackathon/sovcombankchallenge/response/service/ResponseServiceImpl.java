package ru.hackathon.sovcombankchallenge.response.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.stage.models.Interview;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stageResult.models.InterviewResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;
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


    private StageDtoForUser convertToDtoTest(TestStage stage) {
            var result = StageDtoForUser.builder()
                            .id(stage.getId())
                            .name(stage.getName())
                            .deadline(stage.getDeadline())
                            .duration(stage.getDuration())
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
                        .build();
        return result;
    }
}
