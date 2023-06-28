package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;

import java.util.UUID;

@Data
public class CreateInterviewStageDto {
    private String stageName;
    private String comments;
    private UUID vacancyId;
    private StageType stageType;

}
