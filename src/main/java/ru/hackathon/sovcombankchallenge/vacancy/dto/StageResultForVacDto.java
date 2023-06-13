package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
//@Builder
public class StageResultForVacDto {
    private UUID id;
    private String stageName;
    private UUID stageId;
//    private String summary;
//    private LocalDate date;
//    private String linkToZoom;
//    private List<String> answers;
//    private int points;

}
