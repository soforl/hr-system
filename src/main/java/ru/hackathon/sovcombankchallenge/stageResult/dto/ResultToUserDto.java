package ru.hackathon.sovcombankchallenge.stageResult.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResultToUserDto {
    private UUID responseId;
    private UUID resultId;
}
