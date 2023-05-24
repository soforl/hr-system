package ru.hackathon.sovcombankchallenge.response.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "responses")
@RequiredArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @Getter
    @Setter
    private User candidate;
    @ManyToOne
    @Getter
    @Setter
    private Vacancy vacancy;
    @OneToMany(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<StageResult> stageResults;
    @Column(name = "RESPONSE_STATUS")
    @Getter
    @Setter
    private ResponseStatus responseStatus;
    @Column(name = "CREATION_DATE")
    @Getter
    @Setter
    private LocalDate creationDate;
}
