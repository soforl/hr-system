package ru.hackathon.sovcombankchallenge.stage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.CloseQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.OpenQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.repository.QuestionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createOpenQuestion(String text) {
        OpenQuestion question = new OpenQuestion(text);
        return questionRepository.save(question);
    }

    public Question createCloseQuestion(String text, String var1, String var2, String var3, String var4, int rightChoose) {
        CloseQuestion question = new CloseQuestion(text, var1, var2, var3, var4, rightChoose);
        return questionRepository.save(question);
    }

    public Question getById(UUID questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.get();
    }

    public void deleteQuestion(UUID question){
        questionRepository.deleteById(question);
    }

}
