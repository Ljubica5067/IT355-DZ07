package org.example.it355dz07.repository;

import org.example.it355dz07.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface restaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r ORDER BY r.ocena DESC LIMIT 5")
    List<Restaurant> findTop5ByOcena();
}
