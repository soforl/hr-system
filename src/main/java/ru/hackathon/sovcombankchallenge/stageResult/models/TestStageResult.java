package ru.hackathon.sovcombankchallenge.stageResult.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import ru.hackathon.sovcombankchallenge.stage.enumeration.StageType;
import ru.hackathon.sovcombankchallenge.stage.models.CloseQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.models.Stage;
import ru.hackathon.sovcombankchallenge.stage.models.TestStage;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class TestStageResult extends StageResult {
    private List<String> answers;
    private int points;
    public TestStageResult(Stage stage, CustomUser candidate, List<String> answers) {
        super(stage, candidate);
        this.answers = answers;
    }

    public TestStageResult() {

    }

    // TODO: implement counting points

    public int countPoints() {
        TestStage stage = (TestStage) this.getStage();
        int count = 0;
        if (stage.getType().equals(StageType.CloseTest)){
            var rightAnswers = stage.getQuestions().stream().filter(q -> q instanceof CloseQuestion).map(q -> ((CloseQuestion) q).getRightChoose()).toArray();
            var parsedAns = answers.stream().map(Integer::parseInt).toArray();
            for (int i = 0; i<rightAnswers.length; i++){
                if (rightAnswers[i].equals(parsedAns[i])){
                    count++;
                }
            }
        }
        return count;
    }

}
