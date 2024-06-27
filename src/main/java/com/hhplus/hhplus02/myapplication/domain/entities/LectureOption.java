package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "lecture_option")
public class LectureOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("강의 옵션 id")
    private Long lectureOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @Comment("강의 id")
    private Lecture lecture;

    @Column(name = "max_number")
    @Comment("최대 신청 인원")
    private int maxNumber;

    @Column(name = "apply_number")
    @Comment("현재 신청 인원")
    private int applyNumber;

    @Column(nullable = false, name = "start_date_time")
    @Comment("강의 시작일")
    private LocalDateTime startDatetime;

    @Column(nullable = false, name = "end_date_time")
    @Comment("강의 종료일")
    private LocalDateTime endDatetime;

    @Builder
    public LectureOption(Lecture lecture, int maxNumber, int applyNumber, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.lecture = lecture;
        this.maxNumber = maxNumber;
        this.applyNumber = applyNumber;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    public LectureOption(Long lectureOptionId, Lecture lecture, int maxNumber, int applyNumber, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.lectureOptionId = lectureOptionId;
        this.lecture = lecture;
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
