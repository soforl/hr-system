package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;

import java.util.List;

@Entity
@Getter
@Setter
public class TestStageResult extends StageResult {
    private List<String> answers;
    private int points;
    public TestStageResult(Question question, String answer) {
        super();
    }

    public TestStageResult() {

    }
}
