package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class InterviewResult extends StageResult {
    private String summary;
    private LocalDate date;
    private String linkToZoom;

    public InterviewResult(String summary, LocalDate date, String linkToZoom) {
        super();
    }
}
