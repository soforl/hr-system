package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;

import java.util.List;
import java.util.UUID;
@Entity
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    public Question(String text) {
        this.text = text;
    }

    public Question() {

    }
    public UUID getId() {
        return id;
    }
    public String getText() {
        return text;
    }


}
