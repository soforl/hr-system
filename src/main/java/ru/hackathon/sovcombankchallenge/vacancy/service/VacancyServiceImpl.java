package ru.hackathon.sovcombankchallenge.vacancy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.repository.VacancyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService{
    private final VacancyRepository vacancyRepository;

    @Override
    public void create(String description, VacancyStatus status, WorkExperience experience) {
        Vacancy vacancy = new Vacancy(description, status, experience);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void addStage(UUID vacancyId, UUID stageId) {
//        Vacancy vacancy = this.getById(vacancyId);
//        vacancy.getStages().add();
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
//        Vacancy vacancy = this.getById(vacancyId);
//        List<Response> responses = vacancy.
        return null;
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


}
