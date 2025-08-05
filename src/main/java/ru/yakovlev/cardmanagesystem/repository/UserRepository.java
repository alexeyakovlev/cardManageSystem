package ru.yakovlev.cardmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakovlev.cardmanagesystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
