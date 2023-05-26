package ru.hackathon.sovcombankchallenge.stage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.stage.models.OpenQuestion;
import ru.hackathon.sovcombankchallenge.stage.models.Question;
import ru.hackathon.sovcombankchallenge.stage.repository.QuestionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void createOpenQuestion(String text) {
        OpenQuestion question = new OpenQuestion(text);
        questionRepository.save(question);
    }

    public Question getById(UUID questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.get();
    }

}
