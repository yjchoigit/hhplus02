package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface LectureOptionJpaRepository extends JpaRepository<LectureOption, Long> {
    List<LectureOption> findByLectureId(Long lectureId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LectureOption findByLectureIdAndLectureOptionId(Long lectureId, Long lectureOptionId);
}
