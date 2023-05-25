package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SetDateToStageDto {
    private LocalDateTime date;
    private Long stageId;
}
