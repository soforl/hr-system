package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "interview_results")
@RequiredArgsConstructor
public class InterviewResult extends StageResult {
    @Column(name = "SUMMARY")
    @Getter
    @Setter
    private String summary;
    @Column(name = "DATE")
    @Getter
    @Setter
    private LocalDate date;
    @Column(name = "LINK_TO_ZOOM")
    @Getter
    @Setter
    private String linkToZoom;
}
