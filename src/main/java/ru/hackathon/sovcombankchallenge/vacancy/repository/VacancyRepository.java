package ru.hackathon.sovcombankchallenge.vacancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID>, JpaSpecificationExecutor<Vacancy> {
}
