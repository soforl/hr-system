package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class StageDto {
    private UUID stageId;
    private LocalDateTime deadline;
    private Long duration_sec;
    private String stageName;

}
