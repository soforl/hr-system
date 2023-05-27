package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;

import java.util.List;
import java.util.UUID;

@Data
public class SaveUserAnswersToStageDto {
    private UUID stageId;
    private UUID responseId;
    private List<TestStageResult> answers;
}
