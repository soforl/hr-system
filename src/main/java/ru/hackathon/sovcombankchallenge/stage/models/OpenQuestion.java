package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "open_questions")
@RequiredArgsConstructor
public class OpenQuestion extends Question {
    @Column(name = "ANSWER")
    @Getter
    @Setter
    private String answer;
}
