package ru.hackathon.sovcombankchallenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.user.models.Role;

import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {
}
