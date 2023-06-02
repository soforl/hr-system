package ru.hackathon.sovcombankchallenge.vacancy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stage.service.StageService;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.VacancySpecificDto;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService{
    private final VacancyRepository vacancyRepository;
    private final StageService stageService;

    @Override
    public void create(String name, String description, VacancyStatus status, WorkExperience experience) {
        Vacancy vacancy = new Vacancy(name, description, status, experience);
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
    public List<Response> getResponsesByVacancy(UUID vacancyId) {
        Vacancy vacancy = this.getById(vacancyId);
        List<Response> responses = vacancy.getResponses();
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
                            .build()
            );
        }
        return result;
    }

}
