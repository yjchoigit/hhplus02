package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
}
