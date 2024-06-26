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
@Table(name = "lecture")
@Data
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId; // 강의 ID

    @Column(nullable = false, name = "name")
    private String name; // 강의명

    @Column(name = "instructor_name")
    private String instructorName; // 강사명

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
