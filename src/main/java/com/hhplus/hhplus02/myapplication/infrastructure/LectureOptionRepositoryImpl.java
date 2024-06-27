package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureOptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectureOptionRepositoryImpl implements LectureOptionRepository {
    private final LectureOptionJpaRepository lectureOptionJpaRepository;

    public LectureOptionRepositoryImpl(LectureOptionJpaRepository lectureOptionJpaRepository) {
        this.lectureOptionJpaRepository = lectureOptionJpaRepository;
    }

    @Override
    public LectureOption findById(Long lectureOptionId) {
        return lectureOptionJpaRepository.findById(lectureOptionId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public LectureOption findByLectureIdAndLectureOptionId(Long lectureId, Long lectureOptionId) {
        return lectureOptionJpaRepository.findByLecture_LectureIdAndLectureOptionId(lectureId, lectureOptionId);
    }

    @Override
    public List<LectureOption> findByLectureId(Long lectureId) {
        return lectureOptionJpaRepository.findByLecture_LectureId(lectureId);
    }

    @Override
    public void saveAll(List<LectureOption> optionList) {
        lectureOptionJpaRepository.saveAll(optionList);
    }

}
