package ru.hackathon.sovcombankchallenge.vacancy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.service.ResponseService;
import ru.hackathon.sovcombankchallenge.stage.models.*;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;
import ru.hackathon.sovcombankchallenge.stage.task.dto.ReturnStageDto;
import ru.hackathon.sovcombankchallenge.user.dto.ResponseDto;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.StageResultForVacDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.VacancySpecificDto;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService{
    private final VacancyRepository vacancyRepository;
    private final StageService stageService;

    @Override
    public void create(String name, String description, VacancyStatus status, WorkExperience experience,
                       SphereType sphere) {
        Vacancy vacancy = new Vacancy(name, description, status, experience, sphere);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void addStage(UUID vacancyId, UUID stageId) {
        Vacancy vacancy = this.getById(vacancyId);
        Stage stage = stageService.getById(stageId);
        List<Stage> stages = vacancy.getStages();
        stages.add(stage);
        vacancy.setStages(stages);
        vacancyRepository.save(vacancy);
    }

    @Override
    public List<Vacancy> getAll() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancies;
    }

    @Override
    public Vacancy getById(UUID vacancyId) {
        Optional<Vacancy> vacancy = vacancyRepository.findById(vacancyId);
        return vacancy.get();
    }

    @Override
    public List<ResponseDto> getResponsesByVacancy(UUID vacancyId) {
        Vacancy vacancy = this.getById(vacancyId);
        List<ResponseDto> responses = vacancy.getResponses().stream()
                .map(response -> ResponseDto.builder()
                        .vacancyId(vacancyId)
                        .vacancyName(vacancy.getName())
                        .responseStatus(response.getResponseStatus())
                        .user(new UserInfoDto(
                                response.getCandidate().getUsername(),
                                response.getCandidate().getName(),
                                response.getCandidate().getPhoneNumber(),
                                response.getCandidate().getRole(),
                                response.getCandidate().getImage_url()))
                        .creationDate(response.getCreationDate())
                        .results(response.getStageResults().stream().map(res ->
                                new StageResultForVacDto(
                                        res.getId(),
                                        res.getStage().getName(),
                                        res.getId()
                                )
                        ).collect(Collectors.toList())
                ).build()).collect(Collectors.toList());
        return responses;
    }

    @Override
    public List<Stage> getStages(UUID vacancyId) {
        Vacancy vacancy = this.getById(vacancyId);
        List<Stage> stages = vacancy.getStages();
        return stages;
    }

    @Override
    public void updateStatus(UUID vacancyId, VacancyStatus status) {
        Vacancy vacancy = this.getById(vacancyId);
        vacancy.setVacancyStatus(status);
        vacancyRepository.save(vacancy);
    }

    @Override
    public List<ReturnVacancyDto> convertToDtoVacancy(List<Vacancy> vacancies){
        var result = new ArrayList<ReturnVacancyDto>();
        for(var vacancy: vacancies){
            result.add(
                    ReturnVacancyDto.builder()
                            .vacancyId(vacancy.getId())
                            .vacancyName(vacancy.getName())
                            .vacancyStatus(vacancy.getVacancyStatus())
                            .workExperience(vacancy.getWorkExperience())
                            .sphere(vacancy.getSphere())
                            .build()
            );
        }
        return result;
    }

    @Override
    public List<VacancySpecificDto> returnSpecDto(List<Vacancy> vacancies){
        var result = new ArrayList<VacancySpecificDto>();

        for(var vacancy: vacancies){
            result.add(
                    VacancySpecificDto.builder()
                            .vacancyName(vacancy.getName())
                            .description(vacancy.getDescription())
                            .vacancyStatus(vacancy.getVacancyStatus())
                            .workExperience(vacancy.getWorkExperience())
                            .sphereType(vacancy.getSphere())
                            .build()
            );
        }
        return result;
    }

}
