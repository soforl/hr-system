package ru.hackathon.sovcombankchallenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.user.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
