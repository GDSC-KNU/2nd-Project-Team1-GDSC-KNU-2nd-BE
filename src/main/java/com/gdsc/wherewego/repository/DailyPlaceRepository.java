package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.DailyPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPlaceRepository extends JpaRepository<DailyPlace, Long> {
}
