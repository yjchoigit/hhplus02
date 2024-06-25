package com.hhplus.hhplus02.myapplication.domain.repository;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;

import java.util.List;

public interface LectureRepository {
    Lecture findById(Long lectureId);
    List<Lecture> findAll();
}
