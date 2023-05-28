package ru.hackathon.sovcombankchallenge.response.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class StageDtoForUser {
    String name;
    UUID id;
    LocalDateTime deadline;
    String State;
    String result;
    String additional;
}
