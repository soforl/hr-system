package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.time.Duration;
import java.util.UUID;

@Data
public class SetTimeToStageDto {
    private Duration duration;
    private UUID stageId;
}
