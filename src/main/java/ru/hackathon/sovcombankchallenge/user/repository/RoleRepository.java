package ru.hackathon.sovcombankchallenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackathon.sovcombankchallenge.user.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
