package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;

public interface LectureHistoryRepository  {
    void save(LectureHistory lectureHistory);

    LectureHistory findByLectureIdAndLectureOptionIdAndUserId(Long lectureId, Long lectureOptionId, Long userId);

}
