package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaLectureApplyRepository extends JpaRepository<LectureApply, Long> {

    @Query("SELECT count(*) > 0 FROM LectureApply la WHERE la.userId = :userId AND la.lectureId = :lectureId")
    boolean existByLectureIdAndUserId(Long lectureId, Long userId);
}
