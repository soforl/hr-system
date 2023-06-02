package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.models.CloseQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.Question;

import java.util.UUID;

@Data
@AllArgsConstructor
public class QuestionDto {
    private Question question;
    private String type;


    public QuestionDto(Question question) {
        this.question = question;
        this.type = question instanceof CloseQuestion ? "Close" : "Open";
    }
}
