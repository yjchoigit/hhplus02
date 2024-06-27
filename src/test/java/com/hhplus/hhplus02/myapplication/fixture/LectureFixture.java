package com.hhplus.hhplus02.myapplication.fixture;

import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureOptionRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFixture {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureOptionRepository lectureOptionRepository;
    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;

    public Lecture 강의_등록(int maxNumber, int applyNumber){
        Lecture lecture = lectureRepository.save(new Lecture("클린 아키텍처", "홍길동"));

        List<LectureOption> optionList = new ArrayList<>();
        optionList.add(new LectureOption(lecture, maxNumber, applyNumber, LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        lectureOptionRepository.saveAll(optionList);

        return lecture;
    }

    public void 강의_신청내역_등록(Lecture lecture){
        LectureHistory lectureHistory = new LectureHistory(lecture, 1L, 1000L);
        lectureHistoryRepository.save(lectureHistory);
    }
}
