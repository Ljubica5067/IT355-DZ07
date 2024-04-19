package org.example.it355dz07.repository;

import org.example.it355dz07.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface mealRepository extends JpaRepository<Meal,Long> {
    Optional<List<Meal>> findAllByNaziv(String naziv);
}
