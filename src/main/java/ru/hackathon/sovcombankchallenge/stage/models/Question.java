package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import ru.hackathon.sovcombankchallenge.stage.task.dto.QuestionDto;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "questions")
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "TEXT")
    private String text;

    public Question(String text) {
        this.text = text;
    }

    public Question() {

    }
    public UUID getId() {
        return id;
    }


}
