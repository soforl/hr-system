package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.models.Question;

@Entity
@Table(name = "test_stage_results")
@RequiredArgsConstructor
public class TestStageResult extends StageResult {
    @ManyToOne()
    @Getter
    @Setter
    private Question question;
    @Column(name = "USER_ANSWER")
    @Getter
    @Setter
    private String userAnswer;
}
