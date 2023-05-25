package ru.hackathon.sovcombankchallenge.response.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.response.models.Response;
import ru.hackathon.sovcombankchallenge.response.repository.ResponseRepository;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.user.service.UserService;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;
import ru.hackathon.sovcombankchallenge.vacancy.service.VacancyService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService{
    private final ResponseRepository responseRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    @Override
    public void create(UUID candidateId, UUID vacancyId) {
        User candidate = userService.getById(candidateId);
        Vacancy vacancy = vacancyService.getById(vacancyId);
        Response response = new Response(candidate, vacancy);
        responseRepository.save(response);
    }

    @Override
    public List<Response> getAll() {
        List<Response> responses = responseRepository.findAll();
        return responses;
    }
}
