package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_history")
@Data
public class LectureHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureHistoryId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "lecture_id")
    private Long lectureId;

    @CreatedDate
    @Column(nullable = false, name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime;


    public LectureHistory(Long lectureHistoryId, Long userId, Long lectureId, LocalDateTime createDateTime) {
        this.lectureHistoryId = lectureHistoryId;
        this.userId = userId;
        this.lectureId = lectureId;
        this.createDateTime = createDateTime;
    }

    public LectureHistory(Long userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public LectureHistory() {

    }
}
