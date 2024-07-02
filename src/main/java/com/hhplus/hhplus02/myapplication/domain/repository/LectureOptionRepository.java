package com.hhplus.hhplus02.myapplication.domain.repository;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;

import java.util.List;

public interface LectureOptionRepository {
    LectureOption findById(Long lectureOptionId);

    LectureOption findByLectureIdAndLectureOptionId(Long lectureId, Long lectureOptionId);

    List<LectureOption> findByLectureId(Long lectureId);

    void saveAll(List<LectureOption> optionList);
}
