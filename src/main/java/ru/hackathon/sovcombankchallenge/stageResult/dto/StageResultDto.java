package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.dto.UserInfoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class StageResultDto {
    private UUID stage;
    private String stageName;
    private UserInfoDto user;
    private List<String> answers;
    private int points;
    private String summary;
    private LocalDate date;
    private String linkToZoom;
}
