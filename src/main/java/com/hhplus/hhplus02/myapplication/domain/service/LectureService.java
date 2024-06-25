package com.hhplus.hhplus02.myapplication.domain.service;

import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureCheckApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureListApiResDto;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.entities.Users;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureApplyRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.UsersRepository;
import com.hhplus.hhplus02.myapplication.infrastructure.LectureInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class LectureService implements LectureInterface {

    private LectureRepository lectureRepository;
    private UsersRepository usersRepository;
    private LectureHistoryRepository lectureHistoryRepository;
    private LectureApplyRepository lectureApplyRepository;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public LectureApplyApiResDto apply(LectureApplyApiReqDto reqDto) {
        // 회원 정보 조회
        Users user = usersRepository.findById(reqDto.userId());
        // 특강 정보 조회
        Lecture lecture = lectureRepository.findById(reqDto.lectureId());

        // 회원 ID
        Long userId = user.getUserId();
        // 특강 ID
        Long lectureId = lecture.getLectureId();

        // 현재 특강 정보 수강인원 수 확인
        int maxNumber = 30; // 수강인원 최대 30명
        if(lecture.getApplyNumber() >= maxNumber) {// 최대 수강신청 인원이 초과된 경우 실패
            return new LectureApplyApiResDto(userId, lectureId, false);
        }
        
        // 특정 특강을 동일한 회원이 신청했는지 확인
        if(lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId)) { // 이미 신청했으면 실패
            return new LectureApplyApiResDto(userId, lectureId, false);
        }

        // 특강의 수강인원 +1 업데이트
        lecture.updateApplyNumber();

        // 수강신청 내역 저장
        lectureHistoryRepository.save(new LectureHistory(lectureId, userId));

        return new LectureApplyApiResDto(userId, lectureId, true);
    }

    @Override
    public LectureCheckApplyApiResDto checkApply(Long userId, Long lectureId) {
        // 특정 특강을 동일한 회원이 신청했는지 확인
        boolean existApplyYn = lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId);

        return new LectureCheckApplyApiResDto(userId, lectureId, existApplyYn);
    }

    @Override
    public List<LectureListApiResDto> list() {
        List<Lecture> list = lectureRepository.findAll();
        return list.stream().map(LectureListApiResDto::from).toList();
    }
}
