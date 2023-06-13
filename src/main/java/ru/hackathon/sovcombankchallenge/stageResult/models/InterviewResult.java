package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class InterviewResult extends StageResult {
    private String summary;
    private LocalDate date;
    private String linkToZoom;

    public InterviewResult(Stage stage, String summary, LocalDate date, String linkToZoom) {
        super(stage);
        this.summary = summary;
        this.date = date;
        this.linkToZoom = linkToZoom;
    }
}
