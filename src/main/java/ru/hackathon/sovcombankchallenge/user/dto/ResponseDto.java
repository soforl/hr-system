package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ResponseDto {
    private ResponseStatus responseStatus;

    private LocalDate creationDate;
    private String vacancyName;
    private List<StageResultDto> stages;
    private UUID vacancyId;
}
