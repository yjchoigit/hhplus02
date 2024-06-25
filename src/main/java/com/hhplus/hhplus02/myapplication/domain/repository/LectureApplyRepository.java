package com.hhplus.hhplus02.myapplication.domain.repository;

public interface LectureApplyRepository {
    boolean existByLectureIdAndUserId(Long lectureId, Long userId);
}
