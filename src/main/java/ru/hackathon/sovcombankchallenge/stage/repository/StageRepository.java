package ru.hackathon.sovcombankchallenge.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ru.hackathon.sovcombankchallenge.stage.models.Stage;

import java.util.UUID;

public interface StageRepository extends JpaRepository<Stage, UUID>, JpaSpecificationExecutor<Stage> {
}
