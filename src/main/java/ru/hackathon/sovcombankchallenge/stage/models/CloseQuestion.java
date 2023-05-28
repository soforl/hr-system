package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "close_questions")
@Getter
@Setter
public class CloseQuestion extends Question {
    @Column(name = "VAR1")
    private String var1;
    @Column(name = "VAR2")
    private String var2;
    @Column(name = "VAR3")
    private String var3;
    @Column(name = "VAR4")
    private String var4;
    @Column(name = "RIGHT_CHOOSE")
    private int rightChoose;

    public CloseQuestion(String text, String var1, String var2, String var3, String var4, int rightChoose) {
        super(text);
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.rightChoose = rightChoose;
    }

    public CloseQuestion() {

    }
}
