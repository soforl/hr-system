package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

@Data
public class AddStageToUserDto {
    private Long userId;
    private Long stageId;
}
