package ru.hackathon.sovcombankchallenge.vacancy.service;

import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.vacancy.dto.ReturnVacancyDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.VacancySpecificDto;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.List;
import java.util.UUID;

public interface VacancyService {
    void create(String name, String description, VacancyStatus status, WorkExperience experience, SphereType sphere);
    void addStage(UUID vacancyId, UUID stageId);
    List<Vacancy> getAll();
    Vacancy getById(UUID vacancyId);
    List<Stage> getStages(UUID vacancyId);
    void updateStatus(UUID vacancyId, VacancyStatus status);
    List<VacancySpecificDto> returnSpecDto(List<Vacancy> vacancies);
    List<ReturnVacancyDto> convertToDtoVacancy(List<Vacancy> vacancies);
    List<Response> getResponsesByVacancy(UUID vacancyId);
//    List<Vacancy> getByStatus(VacancyStatus status);
//    List<Vacancy> getByName(String name);

}
