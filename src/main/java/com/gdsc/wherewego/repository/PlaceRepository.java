package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
