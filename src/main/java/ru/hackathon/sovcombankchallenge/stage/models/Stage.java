package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;

import java.util.UUID;
@Entity
@Getter
@Setter
public abstract class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private StageType type;

    public Stage(String name, StageType type) {
        this.name = name;
        this.type =  type;
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
