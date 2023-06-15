package ru.hackathon.sovcombankchallenge.response.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stage.enumeration.AccessType;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.StageWithAccess;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.vacancy.dto.StageResultForVacDto;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
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
        this.responseStatus = ResponseStatus.UnderConsideration;
        this.creationDate = LocalDate.now();
        this.stageResults = new ArrayList<>();
        this.access = this.vacancy.getStages().stream().map(StageWithAccess::new).collect(Collectors.toList());
    }
    @OneToMany(fetch = FetchType.EAGER)
    private List<StageResult> stageResults;

    @OneToMany(fetch = FetchType.EAGER)
    private List<StageWithAccess> access;
    @Enumerated(EnumType.STRING)
    private ResponseStatus responseStatus;
    private LocalDate creationDate;

    public void addStageResult(StageResult stage){
        this.stageResults.add(stage);
    }

    public List<Stage> getAccessStages() {
        List<Stage> stages = this.vacancy.getStages();
        var stagesWithAccess = this.access.stream().filter(item ->
                item.getAccess().equals(AccessType.Opened) || item.getAccess().equals(AccessType.Completed)).toList();
        return stagesWithAccess.stream().map(StageWithAccess::getStage).collect(Collectors.toList());
    }
}
