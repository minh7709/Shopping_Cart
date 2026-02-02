package ecomerce.repository;

import ecomerce.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    Optional<Series> findByName(String name);
    boolean existsByName(String name);
}

