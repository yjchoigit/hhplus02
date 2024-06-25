package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.repository.JpaLectureRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final JpaLectureRepository jpaLectureRepository;

    public LectureRepositoryImpl(JpaLectureRepository jpaLectureRepository) {
        this.jpaLectureRepository = jpaLectureRepository;
    }

    @Override
    public Lecture findById(Long lectureId) {
        return jpaLectureRepository.findById(lectureId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Lecture> findAll() {
        return jpaLectureRepository.findAll();
    }
}
