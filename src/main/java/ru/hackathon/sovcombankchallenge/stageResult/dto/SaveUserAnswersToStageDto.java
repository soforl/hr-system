package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stageResult.models.TestStageResult;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SaveUserAnswersToStageDto {
    private UUID stageId;
    private UUID customerId;
    private List<String> answers;
    private UUID responseId;
}
