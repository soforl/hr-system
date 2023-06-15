package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.response.models.Response;

import java.util.UUID;

@Entity
@Getter
@Setter
public class StageWithAccess{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Stage stage;
    private boolean access;

    public StageWithAccess(Stage stage) {
        this.stage = stage;
        this.access = false;
    }

    public StageWithAccess() {

    }
}
