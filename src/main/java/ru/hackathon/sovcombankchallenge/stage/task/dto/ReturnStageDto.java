package ru.hackathon.sovcombankchallenge.stage.task.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;
import ru.hackathon.sovcombankchallenge.stage.models.Question;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor

public class ReturnStageDto {
    private UUID id;
    private String Name;
    private LocalDateTime deadline;
    private long duration;
    List<QuestionDto> questions;
}
