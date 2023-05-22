package ru.hackathon.sovcombankchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
