package ru.hackathon.sovcombankchallenge.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.StageWithAccess;

import java.util.UUID;

public interface StageWithAccessRepository extends JpaRepository<StageWithAccess, UUID> {
}
