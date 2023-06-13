package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.*;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;

import java.util.UUID;
@Entity
public abstract class StageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private Stage stage;
    @ManyToOne
    private CustomUser candidate;

    public StageResult(Stage stage, CustomUser candidate) {
        this.stage = stage;
        this.candidate = candidate;
    }

    public StageResult() {

    }

    public UUID getId() {
        return id;
    }
    public Stage getStage() {
        return stage;
    }
    public CustomUser getCandidate() {
        return candidate;
    }


}
