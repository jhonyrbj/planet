package com.planet.api.repository;

import com.planet.api.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {

    @Transactional
    Optional<Planet> findOneByName(String name);

}
