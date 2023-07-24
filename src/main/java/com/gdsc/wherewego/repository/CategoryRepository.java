package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByScheduleId(Long scheduleId);
}
