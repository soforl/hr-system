package ru.hackathon.sovcombankchallenge.vacancy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vacancy")
@RequiredArgsConstructor
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "VACANCY_STATUS")
    private VacancyStatus vacancyStatus;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Stage> stages;
    @Column(name = "WORK_EXPERIENCE")
    private WorkExperience workExperience;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vacancy")
    private List<Response> responses;

    public Vacancy(String description, VacancyStatus vacancyStatus, WorkExperience workExperience) {
        this.description = description;
        this.vacancyStatus = vacancyStatus;
        this.stages = new ArrayList<>();
        this.workExperience = workExperience;
    }
}
