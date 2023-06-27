package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTestStageDto {
    private StageType stageType;
    private UUID vacancyId;
//    private LocalDateTime deadline;
//    private Long duration_sec;
}
