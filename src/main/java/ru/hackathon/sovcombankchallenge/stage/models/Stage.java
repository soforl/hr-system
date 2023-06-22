package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
public abstract class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    public Stage(String name) {
        this.name = name;
    }

    public Stage() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
