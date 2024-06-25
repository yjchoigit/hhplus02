package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
}
