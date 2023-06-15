package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.response.dto.StageDtoForUser;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;
import ru.hackathon.sovcombankchallenge.stage.models.StageWithAccess;
import ru.hackathon.sovcombankchallenge.stageResult.dto.StageResultDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.StageResultForVacDto;
import ru.hackathon.sovcombankchallenge.vacancy.dto.StageWithAccessDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
    private UUID vacancyId;
    private String vacancyName;
    private ResponseStatus responseStatus;
    private UserInfoDto user;
    private LocalDate creationDate;
    private List<StageDtoForUser> stages;
    private List<StageWithAccessDto> stageWithAccessDtos;
    private List<StageResultForVacDto> results;

}
