package com.hhplus.hhplus02.myapplication.controllers.dto;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record LectureListApiResDto(
        // 강의 ID
        Long lectureId,
        // 강의명
        String name,
        // 강사명
        String instructorName,
        // 강의 옵션 리스트
        List<LectureOptionApiResDto> optionList
) implements Serializable {

    public record LectureOptionApiResDto(
            // 강의 옵션 ID
            Long lectureOptionId,
            // 최대 신청 인원 수
            int maxNumber,
            // 강의 시작일
            LocalDateTime startDatetime,
            // 강의 종료일
            LocalDateTime endDatetime
    ) implements Serializable{
        public static LectureOptionApiResDto from(LectureOption entity) {
            return new LectureOptionApiResDto(entity.getLectureOptionId(), entity.getMaxNumber(), entity.getStartDatetime(), entity.getEndDatetime());
        }
    }

    public static LectureListApiResDto from(Lecture entity, List<LectureOption> list) {
        return new LectureListApiResDto(
                entity.getLectureId(), entity.getName(), entity.getInstructorName(),
                list.stream().map(LectureOptionApiResDto::from).toList()
        );
    }
}
