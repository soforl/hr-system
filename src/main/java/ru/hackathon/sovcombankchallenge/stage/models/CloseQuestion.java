package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "close_questions")
@RequiredArgsConstructor
public class CloseQuestion extends Question {
    @Column(name = "VAR1")
    @Getter
    @Setter
    private String var1;
    @Column(name = "VAR2")
    @Getter
    @Setter
    private String var2;
    @Column(name = "VAR3")
    @Getter
    @Setter
    private String var3;
    @Column(name = "VAR4")
    @Getter
    @Setter
    private String var4;
    @Column(name = "RIGHT_CHOOSE")
    @Getter
    @Setter
    private int rightChoose;
}
