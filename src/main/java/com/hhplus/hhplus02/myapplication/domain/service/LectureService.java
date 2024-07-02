package com.hhplus.hhplus02.myapplication.domain.service;

import com.hhplus.hhplus02.myapplication.controllers.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCancelApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCreateApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureListApiResDto;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureOptionRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class LectureService implements LectureInterface {

    private LectureRepository lectureRepository;
    private LectureOptionRepository lectureOptionRepository;
    private LectureHistoryRepository lectureHistoryRepository;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean apply(LectureApplyApiReqDto reqDto) {

        // 강의 조회
        Lecture lecture = lectureRepository.findById(reqDto.lectureId());

        // 강의 옵션 조회
        LectureOption lectureOption = lectureOptionRepository.findByLectureIdAndLectureOptionId(lecture.getLectureId(), reqDto.lectureOptionId());

        // 강의 내역에 신청 내역이 들어있는지 확인
        LectureHistory lectureHistory = lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(lecture.getLectureId(), lectureOption.getLectureOptionId(), reqDto.userId());
        if(lectureHistory != null && lectureHistory.checkApplyComplete()){
            throw new IllegalArgumentException("이미 신청된 강의입니다.");
        }

        // 신청인원 증가
        lectureOption.increaseApplyNumber();

        // 강의 신청 내역 저장
        lectureHistoryRepository.save(new LectureHistory(lecture, reqDto.lectureOptionId(), reqDto.userId()));

        return true;
    }

    @Override
    public boolean checkApply(Long userId, Long lectureId, Long lectureOptionId) {
        // 강의 내역에 신청 내역이 들어있는지 확인
        LectureHistory lectureHistory = lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(lectureId, lectureOptionId, userId);
        // 강의 신청 여부 확인
        return lectureHistory != null && lectureHistory.checkApplyComplete();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void cancel(LectureCancelApiReqDto reqDto) {
        // 강의 조회
        Lecture lecture = lectureRepository.findById(reqDto.lectureId());

        // 강의 옵션 조회
        LectureOption lectureOption = lectureOptionRepository.findByLectureIdAndLectureOptionId(lecture.getLectureId(), reqDto.lectureOptionId());

        // 강의 내역에 신청 내역이 들어있는지 확인
        LectureHistory lectureHistory = lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(lecture.getLectureId(), reqDto.lectureOptionId(), reqDto.userId());

        // 강의 신청 내역이 없으면 실패
        if(lectureHistory == null){
            throw new IllegalArgumentException("강의 신청내역이 없습니다.");
        }

        // 강의 취소 여부 확인
        lectureHistory.checkCancelComplete();

        // 신청인원 감소
        lectureOption.decreaseApplyNumber();

        // 강의 취소
        lectureHistory.cancel();
    }

    @Override
    public List<LectureListApiResDto> list() {
        List<LectureListApiResDto> result = new ArrayList<>();

        // 전체 강의 목록 조회
        List<Lecture> list = lectureRepository.findAll();

        // 강의 별 강의 옵션 목록 조회
        for(Lecture lecture : list) {
            List<LectureOption> optionList = lectureOptionRepository.findByLectureId(lecture.getLectureId());
            result.add(LectureListApiResDto.from(lecture, optionList));
        }

        return result;
    }

    @Override
    public Long create(LectureCreateApiReqDto reqDto) {
        // 강의 등록
        Lecture lecture = lectureRepository.save(new Lecture(reqDto.name(), reqDto.instructorName()));

        // 강의 옵션 등록
        List<LectureOption> optionList = reqDto.optionList().stream()
                .map(m -> m.toEntity(lecture))
                .toList();
        lectureOptionRepository.saveAll(optionList);

        return lecture.getLectureId();
    }

}
