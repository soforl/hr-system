package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.stage.models.CloseQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.Question;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class QuestionDto {
    private String question;
    private UUID id;
    private String type;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private int rightChoose;
}
