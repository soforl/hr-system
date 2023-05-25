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
@Getter
@Setter
public class InterviewResult extends StageResult {
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "LINK_TO_ZOOM")
    private String linkToZoom;
}
