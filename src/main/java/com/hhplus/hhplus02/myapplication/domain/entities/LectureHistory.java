package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_history")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LectureHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureHistoryId; // 강의 신청 ID

    @Column(nullable = false, name = "lecture_id")
    private Long lectureId; // 강의 ID

    @Column(nullable = false, name = "lecture_option_id")
    private Long lectureOptionId; // 강의 옵션 ID

    @Column(nullable = false, name = "user_id")
    private Long userId; // 회원 ID

    @CreatedDate
    @Column(nullable = false, name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime; // 강의 신청 등록일

    @LastModifiedDate
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime; // 강의 신청 수정일

    @Column(name = "cancel_date_time")
    private LocalDateTime cancelDateTime; // 강의 신청 취소일

    @Builder
    public LectureHistory(Long lectureId, Long lectureOptionId,  Long userId) {
        this.lectureId = lectureId;
        this.lectureOptionId = lectureOptionId;
        this.userId = userId;
    }
    
    // 강의 신청 취소
    public void cancel(){
        this.cancelDateTime = LocalDateTime.now();
    }

    // 강의 신청 여부 확인
    public boolean checkApplyComplete(){
        return this.createDateTime != null && this.cancelDateTime == null;
    }

    // 강의 취소 여부 확인
    public boolean checkCancelComplete(){
        return this.cancelDateTime != null;
    }

}
