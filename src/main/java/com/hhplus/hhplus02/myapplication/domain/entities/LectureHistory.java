package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Table(name = "lecture_history")
public class LectureHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("강의 신청 id")
    private Long lectureHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @Comment("강의 id")
    private Lecture lecture;

    @Column(nullable = false, name = "lecture_option_id")
    @Comment("강의 옵션 id")
    private Long lectureOptionId;

    @Column(nullable = false, name = "user_id")
    @Comment("회원 id")
    private Long userId;

    @CreatedDate
    @Column(nullable = false, name = "create_date_time", updatable = false)
    @Comment("강의 신청 등록일")
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "update_date_time")
    @Comment("강의 신청 수정일")
    private LocalDateTime updateDateTime;

    @Column(name = "cancel_date_time")
    @Comment("강의 신청 취소일")
    private LocalDateTime cancelDateTime;

    @PrePersist
    public void prePersist() {
        this.updateDateTime = null;
    }

    @Builder
    public LectureHistory(Lecture lecture, Long lectureOptionId,  Long userId) {
        this.lecture = lecture;
        this.lectureOptionId = lectureOptionId;
        this.userId = userId;
    }

    public LectureHistory(Long lectureHistoryId, Lecture lecture, Long lectureOptionId, Long userId, LocalDateTime createDateTime, LocalDateTime updateDateTime, LocalDateTime cancelDateTime) {
        this.lectureHistoryId = lectureHistoryId;
        this.lecture = lecture;
        this.lectureOptionId = lectureOptionId;
        this.userId = userId;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
        this.cancelDateTime = cancelDateTime;
    }

    // 강의 신청 취소
    public void cancel(){
        this.cancelDateTime = LocalDateTime.now();
    }

    // 강의 신청 여부 확인
    public boolean checkApplyComplete(){
        return this.cancelDateTime == null;
    }

    // 강의 취소 여부 확인
    public boolean checkCancelComplete(){
        return this.cancelDateTime != null;
    }

}
