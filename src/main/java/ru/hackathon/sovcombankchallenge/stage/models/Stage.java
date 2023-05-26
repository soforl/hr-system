package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Entity
@Table(name = "stages")
public abstract class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "NAME")
    private String name;

    public Stage(String name) {
        this.name = name;
    }

    public Stage() {

    }
}
