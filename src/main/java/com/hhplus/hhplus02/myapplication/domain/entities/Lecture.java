package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("강의 id")
    private Long lectureId;
    
    @Column(nullable = false, name = "name")
    @Comment("강의명")
    private String name;

    @Column(name = "instructor_name")
    @Comment("강사명")
    private String instructorName;

    public Lecture(Long lectureId, String name, String instructorName) {
        this.lectureId = lectureId;
        this.name = name;
        this.instructorName = instructorName;
    }

    @Builder
    public Lecture(String name, String instructorName) {
        this.name = name;
        this.instructorName = instructorName;
    }


    // 수정
    public void update(String name, String instructorName){
        this.name = name;
        this.instructorName = instructorName;
    }
}
