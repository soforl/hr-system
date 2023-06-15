package ru.hackathon.sovcombankchallenge.stage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hackathon.sovcombankchallenge.stage.enumeration.AccessType;

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
    @Enumerated(EnumType.STRING)
    private AccessType access;

    public StageWithAccess(Stage stage) {
        this.stage = stage;
        this.access = AccessType.Opened;
    }

    public StageWithAccess() {

    }
}
