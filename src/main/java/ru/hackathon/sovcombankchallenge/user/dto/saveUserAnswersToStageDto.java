package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class saveUserAnswersToStageDto {
    private UUID stageId;
    private UUID responseId;
    private List<String> answers;
}
