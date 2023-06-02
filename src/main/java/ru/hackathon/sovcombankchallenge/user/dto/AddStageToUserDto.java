package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddStageToUserDto {
    private UUID userId;
    private UUID stageId;
}
