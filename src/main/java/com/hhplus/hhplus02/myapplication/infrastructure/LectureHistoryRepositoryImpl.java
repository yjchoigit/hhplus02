package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.repository.JpaLectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {
    private final JpaLectureHistoryRepository jpaLectureHistoryRepository;

    public LectureHistoryRepositoryImpl(JpaLectureHistoryRepository jpaLectureHistoryRepository) {
        this.jpaLectureHistoryRepository = jpaLectureHistoryRepository;
    }

    @Override
    public void save(LectureHistory lectureHistory) {
        jpaLectureHistoryRepository.save(lectureHistory);
    }


}
