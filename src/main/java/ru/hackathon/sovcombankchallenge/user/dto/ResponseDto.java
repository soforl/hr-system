package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
    private ResponseStatus responseStatus;

    private LocalDate creationDate;
    private String vacancyName;
    private List<StageDtoForUser> stages;
    private UUID vacancyId;
}
