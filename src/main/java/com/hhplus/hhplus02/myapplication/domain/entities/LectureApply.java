package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_apply")
@Data
public class LectureApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureApplyId;

    @Column(nullable = false, name = "lecture_id")
    private Long lectureId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @CreatedDate
    @Column(nullable = false, name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime;

    public LectureApply(Long lectureApplyId, Long userId, Long lectureId, LocalDateTime createDateTime) {
        this.lectureApplyId = lectureApplyId;
        this.lectureId = lectureId;
        this.createDateTime = createDateTime;
    }

    public LectureApply(Long userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public LectureApply() {

    }
}
