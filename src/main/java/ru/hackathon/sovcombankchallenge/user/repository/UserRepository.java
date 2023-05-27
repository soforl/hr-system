package ru.hackathon.sovcombankchallenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hackathon.sovcombankchallenge.user.models.User;
import ru.hackathon.sovcombankchallenge.vacancy.models.Vacancy;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);
}
