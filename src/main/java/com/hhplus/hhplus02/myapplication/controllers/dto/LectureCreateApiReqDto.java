package com.hhplus.hhplus02.myapplication.controllers.dto;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record LectureCreateApiReqDto(
        // 강의 명
        String name,
        // 강사 명
        String instructorName,
        // 강의 옵션 리스트
        List<LectureOptionApiReqDto> optionList
) implements Serializable {

    public record LectureOptionApiReqDto(
            // 최대 신청 인원 수
            int maxNumber,
            // 강의 시작일
            LocalDateTime startDatetime,
            // 강의 종료일
            LocalDateTime endDatetime
    ) implements Serializable{

        public LectureOption toEntity(Lecture lecture){
            return LectureOption.builder()
                    .lecture(lecture)
                    .maxNumber(maxNumber)
                    .startDatetime(startDatetime)
                    .endDatetime(endDatetime)
                    .build();
        }
    }
}
