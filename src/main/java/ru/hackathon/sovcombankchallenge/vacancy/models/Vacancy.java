package ru.hackathon.sovcombankchallenge.vacancy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vacancies")
@RequiredArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;
    @Column(name = "VACANCY_STATUS")
    @Getter
    @Setter
    private VacancyStatus vacancyStatus;
    @OneToMany(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<Stage> stages;
    @Column(name = "WORK_EXPERIENCE")
    @Getter
    @Setter
    private WorkExperience workExperience;

    public Vacancy(String description, VacancyStatus vacancyStatus, WorkExperience workExperience) {
        this.description = description;
        this.vacancyStatus = vacancyStatus;
        this.stages = new ArrayList<>();
        this.workExperience = workExperience;
    }
}
