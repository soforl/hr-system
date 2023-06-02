package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SetDateToStageDto {
    private LocalDateTime date;
    private UUID stageId;
}
