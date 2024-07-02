package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {
    private final LectureHistoryJpaRepository lectureHistoryJpaRepository;

    public LectureHistoryRepositoryImpl(LectureHistoryJpaRepository lectureHistoryJpaRepository) {
        this.lectureHistoryJpaRepository = lectureHistoryJpaRepository;
    }

    @Override
    public void save(LectureHistory lectureHistory) {
        lectureHistoryJpaRepository.save(lectureHistory);
    }

    @Override
    public LectureHistory findByLectureIdAndLectureOptionIdAndUserId(Long lectureId, Long lectureOptionId, Long userId) {
        return lectureHistoryJpaRepository.findFirstByLecture_LectureIdAndLectureOptionIdAndUserId(lectureId, lectureOptionId, userId);
    }

}
