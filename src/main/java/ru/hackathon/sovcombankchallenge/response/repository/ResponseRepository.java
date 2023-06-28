package ru.hackathon.sovcombankchallenge.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.UUID;

public interface ResponseRepository extends JpaRepository<Response, UUID>, JpaSpecificationExecutor<Response> {
    Response findByVacancyAndCandidate(Vacancy vacancy, CustomUser candidate);
}
