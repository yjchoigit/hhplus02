package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.repository.JpaLectureApplyRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureApplyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LectureApplyRepositoryImpl implements LectureApplyRepository {
    private final JpaLectureApplyRepository jpaLectureApplyRepository;

    public LectureApplyRepositoryImpl(JpaLectureApplyRepository jpaLectureApplyRepository) {
        this.jpaLectureApplyRepository = jpaLectureApplyRepository;
    }

    @Override
    public boolean existByLectureIdAndUserId(Long lectureId, Long userId) {
        return jpaLectureApplyRepository.existByLectureIdAndUserId(lectureId, userId);
    }
}
