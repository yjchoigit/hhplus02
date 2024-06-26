package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistory, Long> {
    @Query("SELECT lh FROM LectureHistory lh WHERE lectureId = :lectureId AND lectureOptionId = :lectureOptionId AND userId = :userId")
    LectureHistory findFirstByLectureIdAndLectureOptionIdAndUserId(Long lectureId, Long lectureOptionId, Long userId);

}
