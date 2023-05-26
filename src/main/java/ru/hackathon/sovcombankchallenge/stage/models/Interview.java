package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "interviews")
@Getter
@Setter
@RequiredArgsConstructor
public class Interview extends Stage {
    @Column(name = "COMMENTS")
    private String comments;

    public Interview(String name, String comments) {
        super(name);
        this.comments = comments;
    }
}
