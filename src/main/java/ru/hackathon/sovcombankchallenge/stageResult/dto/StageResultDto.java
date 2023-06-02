package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StageResultDto {
    private UUID stage;
    private String stageName;
}
