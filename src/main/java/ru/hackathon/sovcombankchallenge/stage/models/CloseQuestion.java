package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CloseQuestion extends Question {
    private String var1;
    private String var2;
    private String var3;
    private String var4;
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
