package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "lecture_option")
@Data
@NoArgsConstructor
public class LectureOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureOptionId; // 강의 옵션 ID

    @Column(nullable = false, name = "lecture_id")
    private Long lectureId; // 강의 ID

    @Column(name = "max_number")
    private int maxNumber; // 최대 신청 인원

    @Column(name = "apply_number")
    private int applyNumber; // 현재 신청 인원

    @Column(nullable = false, name = "start_date_time")
    private LocalDateTime startDatetime; // 강의 시작일

    @Column(nullable = false, name = "end_date_time")
    private LocalDateTime endDatetime; // 강의 종료일

    @Builder
    public LectureOption(Long lectureId, int maxNumber, int applyNumber, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.lectureId = lectureId;
        this.maxNumber = maxNumber;
        this.applyNumber = applyNumber;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    // 신청 인원 증가
    public void increaseApplyNumber() {
        if(maxNumber <= applyNumber){
            throw new IllegalArgumentException("신청 인원 초과");
        }
        this.applyNumber++;
    }

    // 신청 인원 감소
    public void decreaseApplyNumber() {
        if(applyNumber == 0){
            throw new IllegalArgumentException("신청 인원이 현재 없습니다.");
        }
        this.applyNumber--;
    }

}

