package com.hhplus.hhplus02;


import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureCheckApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureListApiResDto;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.Users;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureApplyRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.UsersRepository;
import com.hhplus.hhplus02.myapplication.domain.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private LectureHistoryRepository lectureHistoryRepository;
    @Mock
    private LectureApplyRepository lectureApplyRepository;

    private LectureService lectureService;

    @BeforeEach
    void setUp() {
        lectureService = new LectureService(lectureRepository, usersRepository, lectureHistoryRepository, lectureApplyRepository);
    }

    @DisplayName("특강의 신청인원이 30명 미만일 때 특정 유저가 특정 특강을 신청을 성공한다.")
    @Test
    void maxNumber_30_under_apply_success(){
        Long userId = 1L;
        Long lectureId = 1L;
        Users user = new Users(userId, "테스트");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 06, 29, 13, 00);
        Lecture lecture = new Lecture(lectureId, "토요일 특강", 30, 1, localDateTime);

        when(usersRepository.findById(userId)).thenReturn(user);
        when(lectureRepository.findById(lectureId)).thenReturn(lecture);
        when(lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId)).thenReturn(false);

        LectureApplyApiResDto result = lectureService.apply(new LectureApplyApiReqDto(userId, lectureId));

        assertTrue(result.successYn());
    }

    @DisplayName("특강의 신청인원이 30명 초과일 때 특정 유저가 특정 특강 신청을 실패한다.")
    @Test
    void maxNumber_30_over_apply_fail(){
        Long userId = 1L;
        Long lectureId = 1L;
        Users user = new Users(userId, "테스트");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 06, 29, 13, 00);
        Lecture lecture = new Lecture(lectureId, "토요일 특강", 30, 1, localDateTime);

        when(usersRepository.findById(userId)).thenReturn(user);
        when(lectureRepository.findById(lectureId)).thenReturn(lecture);
        when(lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId)).thenReturn(true);

        LectureApplyApiResDto result = lectureService.apply(new LectureApplyApiReqDto(userId, lectureId));

        assertTrue(!result.successYn());
    }

    @DisplayName("특정 특강을 동일한 회원이 신청했는지 확인")
    @Test
    void apply_checkApply_success(){
        Long userId = 1L;
        Long lectureId = 1L;

        when(lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId)).thenReturn(true);

        LectureCheckApplyApiResDto result = lectureService.checkApply(userId, lectureId);
        assertTrue(result.successYn());
    }

    @DisplayName("특정 특강을 동일한 회원이 신청하지 않았는지 확인")
    @Test
    void apply_checkApply_fail(){
        Long userId = 1L;
        Long lectureId = 1L;

        when(lectureApplyRepository.existByLectureIdAndUserId(lectureId, userId)).thenReturn(false);

        LectureCheckApplyApiResDto result = lectureService.checkApply(userId, lectureId);
        assertTrue(!result.successYn());
    }

    @DisplayName("특강 목록 조회 성공")
    @Test
    void list_success(){
        Long userId = 1L;
        Long lectureId = 1L;

        LocalDateTime localDateTime = LocalDateTime.of(2024, 06, 29, 13, 00);
        Lecture lecture = new Lecture(lectureId, "토요일 특강", 30, 1, localDateTime);

        when(lectureRepository.findAll()).thenReturn(List.of(lecture));

        List<LectureListApiResDto> result = lectureService.list();
        assertEquals(result.size(), 1);
    }
}
