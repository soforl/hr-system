package ru.hackathon.sovcombankchallenge.stageResult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;

import java.util.UUID;

public interface StageResultRepository extends JpaRepository<StageResult, UUID> {
}
