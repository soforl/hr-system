package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interviews")
@RequiredArgsConstructor
@Getter
@Setter
public class Interview extends Stage {
    @Column(name = "COMMENTS")
    private String comments;
}
