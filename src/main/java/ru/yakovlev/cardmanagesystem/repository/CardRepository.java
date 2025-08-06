package ru.yakovlev.cardmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakovlev.cardmanagesystem.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
