package ru.hackathon.sovcombankchallenge.response.service;

import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.List;
import java.util.UUID;

public interface ResponseService {
    void create(UUID candidateId, UUID vacancyId);
    List<Response> getAll();
    List<Response> getResponsesByVacancy(UUID vacancyId);
}
