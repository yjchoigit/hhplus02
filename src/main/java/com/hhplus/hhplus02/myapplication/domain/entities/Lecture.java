package com.hhplus.hhplus02.myapplication.domain.entities;

import jakarta.persistence.*;
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
    private Long lectureId;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "max_number")
    private int maxNumber;

    @Column(name = "apply_number")
    private int applyNumber;

    @Column(nullable = false, name = "lecuture_date_time")
    private LocalDateTime lectureDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private List<LectureApply> lectureApplyList = new ArrayList();

    public void updateApplyNumber(){
        this.applyNumber = this.applyNumber + 1;
    }

    public Lecture(Long lectureId, String name, int maxNumber, int applyNumber, LocalDateTime lectureDateTime) {
        this.lectureId = lectureId;
        this.name = name;
        this.maxNumber = maxNumber;
        this.applyNumber = applyNumber;
        this.lectureDateTime = lectureDateTime;
    }
}
