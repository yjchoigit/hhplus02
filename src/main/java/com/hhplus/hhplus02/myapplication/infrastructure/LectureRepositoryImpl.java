package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    public LectureRepositoryImpl(LectureJpaRepository lectureJpaRepository) {
        this.lectureJpaRepository = lectureJpaRepository;
    }

    @Override
    public Lecture findById(Long lectureId) {
        return lectureJpaRepository.findById(lectureId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Lecture> findAll() {
        return lectureJpaRepository.findAll();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }
}
