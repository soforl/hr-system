package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.*;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.UUID;
@Entity
public abstract class StageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private Stage stage;

    public UUID getId() {
        return id;
    }
}
