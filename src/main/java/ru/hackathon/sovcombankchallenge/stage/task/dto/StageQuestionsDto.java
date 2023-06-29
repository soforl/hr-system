package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StageQuestionsDto {
    private UUID stageId;
    private UUID userId;
}
