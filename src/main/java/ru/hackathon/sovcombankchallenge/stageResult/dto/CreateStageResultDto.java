package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateStageResultDto {
    private UUID stageId;
    private String result;

}
