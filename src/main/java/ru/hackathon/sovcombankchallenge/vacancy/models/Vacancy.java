package ru.hackathon.sovcombankchallenge.vacancy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Enumerated(EnumType.STRING)
    private VacancyStatus vacancyStatus;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Stage> stages;
    @Enumerated(EnumType.STRING)
    private WorkExperience workExperience;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vacancy", cascade = CascadeType.ALL)
    private List<Response> responses;

    @Enumerated(EnumType.STRING)
    private SphereType sphere;

    public Vacancy(String name, String description, VacancyStatus vacancyStatus, WorkExperience workExperience,
                   SphereType sphere) {
        this.name = name;
        this.description = description;
        this.vacancyStatus = vacancyStatus;
        this.stages = new ArrayList<>();
        this.workExperience = workExperience;
        this.sphere = sphere;
        this.responses = new ArrayList<>();
    }

//    public List<StageResultDto> convertToDto(){
//        List<StageResultDto> dtos = new ArrayList<>();
//        for (Stage result: stages) {
//            dtos.add(new StageResultDto(result.getId(), result.getName()));
//        }
//        return dtos;
//    }
}
