package ru.hackathon.sovcombankchallenge.response.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "responses")
@RequiredArgsConstructor
@Getter
@Setter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private CustomUser candidate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    public Response(CustomUser candidate, Vacancy vacancy) {
        this.candidate = candidate;
        this.vacancy = vacancy;
        this.stageResults = new ArrayList<>();
        this.responseStatus = ResponseStatus.Generated;
        this.creationDate = LocalDate.now();
        this.stageResults = new ArrayList<>();
    }

    @OneToMany(fetch = FetchType.EAGER)
    private List<StageResult> stageResults;
    @Column(name = "RESPONSE_STATUS")
    @Enumerated(EnumType.STRING)
    private ResponseStatus responseStatus;
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    public void addStageResult(StageResult stage){
        this.stageResults.add(stage);
    }


}
