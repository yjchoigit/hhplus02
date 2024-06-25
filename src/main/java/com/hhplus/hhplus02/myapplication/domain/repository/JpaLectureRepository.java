package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaLectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findById(Long lectureId);

    List<Lecture> findAll();
}
