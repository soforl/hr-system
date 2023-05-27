package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateStageResultDto {
    private UUID responseId;
    private LocalDate date;
    private String linkToZoom;

}
