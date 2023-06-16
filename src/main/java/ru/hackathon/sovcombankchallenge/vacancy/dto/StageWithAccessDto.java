package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.hackathon.sovcombankchallenge.stage.enumeration.AccessType;

import java.util.UUID;

@Builder
@AllArgsConstructor
public class StageWithAccessDto {
    private UUID stageId;
    private AccessType access;
}
