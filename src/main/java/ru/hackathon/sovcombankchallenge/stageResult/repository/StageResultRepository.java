package ru.hackathon.sovcombankchallenge.stageResult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stageResult.models.StageResult;

import java.util.UUID;


public interface StageResultRepository extends JpaRepository<StageResult, UUID>, JpaSpecificationExecutor<StageResult> {
}
