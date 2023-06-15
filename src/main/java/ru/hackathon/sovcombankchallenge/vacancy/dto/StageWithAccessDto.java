package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@AllArgsConstructor
public class StageWithAccessDto {
    private UUID stageId;
    private Boolean access;
}
