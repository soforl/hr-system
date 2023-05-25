package ru.hackathon.sovcombankchallenge.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.response.models.Response;

import java.util.UUID;

public interface ResponseRepository extends JpaRepository<Response, UUID> {
}
