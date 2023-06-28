package ru.hackathon.sovcombankchallenge.response.dto;

import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class StageDtoForUser {
    String name;
    UUID id;
    LocalDateTime deadline;
    Duration duration;
    String comments;
    StageType type;
}
