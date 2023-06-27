package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class TestStage extends Stage {
    private LocalDateTime deadline;
    private Duration duration;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Question> questions;

    public TestStage(String name, StageType type, LocalDateTime deadline, Duration duration) {
        super(name, type);
        this.deadline = deadline;
        this.duration = duration;
        this.questions = new ArrayList<>();
    }

//    public List<QuestionDto> convertToDto(){
//        List<QuestionDto> dto = new ArrayList<>();
//        for (Question q: questions) {
//            dto.add(new QuestionDto(q));
//        }
//        return dto;
//    }
}
