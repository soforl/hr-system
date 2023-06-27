package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Interview extends Stage {
    private String comments;

    public Interview(String name, StageType type, String comments) {
        super(name, type);
        this.comments = comments;
    }
}
