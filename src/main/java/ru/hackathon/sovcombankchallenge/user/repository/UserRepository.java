package ru.hackathon.sovcombankchallenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;

import java.util.UUID;


public interface UserRepository extends JpaRepository<CustomUser, UUID>, JpaSpecificationExecutor<CustomUser> {
    CustomUser findByEmail(String email);
}
