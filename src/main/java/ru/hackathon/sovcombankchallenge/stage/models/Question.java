package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;

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
    }

    public Question() {

    }
    public UUID getId() {
        return id;
    }
}
