package com.hhplus.hhplus02.myapplication.domain.dto;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;

import java.io.Serializable;
import java.time.LocalDateTime;

public record LectureListApiResDto(
        // 특강 ID
        Long lectureId,
        // 특강명
        String name,
        // 최대 신청인원
        int maxNumber,
        // 현재 신청인원
        int applyNumber,
        // 특강일
        LocalDateTime lectureDateTime
) implements Serializable {
    public static LectureListApiResDto from(Lecture entity) {
        return new LectureListApiResDto(
                entity.getLectureId(), entity.getName(), entity.getMaxNumber(),
                entity.getApplyNumber(), entity.getLectureDateTime()
        );
    }
}
