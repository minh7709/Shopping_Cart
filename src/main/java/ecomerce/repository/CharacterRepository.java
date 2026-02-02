package ecomerce.repository;

import ecomerce.entity.CharacterEntity;
import ecomerce.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {
    Optional<CharacterEntity> findByName(String name);
    List<CharacterEntity> findBySeriesId(Integer seriesId);
    boolean existsByName(String name);
}

