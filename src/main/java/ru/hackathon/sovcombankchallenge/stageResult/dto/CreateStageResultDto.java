package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateStageResultDto {
    private UUID responseId;
    private UUID stageResultId;
    private String summary;
    private LocalDate date;
    private String linkToZoom;

}
