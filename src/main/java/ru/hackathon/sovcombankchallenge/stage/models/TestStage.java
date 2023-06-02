package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "test_stages")
@RequiredArgsConstructor
@Getter
@Setter
public class TestStage extends Stage {
    @Column(name = "DEADLINE")
    private LocalDateTime deadline;
    @Column(name = "DURATION")
    private Duration duration;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Question> questions;

    public TestStage(String name, LocalDateTime deadline, Duration duration) {
        super(name);
        this.deadline = deadline;
        this.duration = duration;
        this.questions = new ArrayList<>();
    }

    public List<QuestionDto> convertToDto(){
        List<QuestionDto> dto = new ArrayList<>();
        for (Question q: questions) {
            dto.add(new QuestionDto(q));
        }
        return dto;
    }
}
