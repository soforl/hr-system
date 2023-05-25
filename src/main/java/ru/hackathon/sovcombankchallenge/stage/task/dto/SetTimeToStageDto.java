package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class SetTimeToStageDto {
    private Duration duration;
    private Long stageId;
}
