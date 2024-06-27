package com.hhplus.hhplus02.myapplication.service;


import com.hhplus.hhplus02.myapplication.controllers.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureHistory;
import com.hhplus.hhplus02.myapplication.domain.entities.LectureOption;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureHistoryRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureOptionRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.LectureRepository;
import com.hhplus.hhplus02.myapplication.domain.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private LectureOptionRepository lectureOptionRepository;

    @Mock
    private LectureHistoryRepository lectureHistoryRepository;

    private Lecture lecture;
    private List<LectureOption> lectureOptions;

    @BeforeEach
    void setUp() {
        // 강의 등록
        lecture = new Lecture(1L, "자바 기초", "홍길동");

        List<LectureOption> optionList = new ArrayList<>();
        optionList.add(new LectureOption(1L, lecture, 30, 0, LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        lectureOptions = optionList;
    }

    @Test
    @DisplayName("강의 신청 성공")
    void apply_suceess() {
        // given
        LectureApplyApiReqDto reqDto = new LectureApplyApiReqDto(1000L, 1L, 1L);
        when(lectureRepository.findById(anyLong())).thenReturn(lecture);
        when(lectureOptionRepository.findByLectureIdAndLectureOptionId(anyLong(), anyLong())).thenReturn(lectureOptions.get(0));
        when(lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        // when
        boolean result = lectureService.apply(reqDto);

        //then
        assertTrue(result);
        verify(lectureHistoryRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("강의 신청 실패 - 최대 신청인원 수를 초과했을 때")
    void apply_maxNumber_over_fail() {
        // given
        LectureApplyApiReqDto reqDto = new LectureApplyApiReqDto(1000L, 1L, 1L);

        List<LectureOption> optionList = new ArrayList<>();
        optionList.add(new LectureOption(1L, lecture, 30, 30, LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        lectureOptions = optionList;

        when(lectureRepository.findById(anyLong())).thenReturn(lecture);
        when(lectureOptionRepository.findByLectureIdAndLectureOptionId(anyLong(), anyLong())).thenReturn(lectureOptions.get(0));
        when(lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        // when
        assertThrows(IllegalArgumentException.class, () -> {
            lectureService.apply(reqDto);
        });

        // then
        verify(lectureHistoryRepository, never()).save(any());
    }

    @Test
    @DisplayName("강의 신청 실패 - 이미 신청된 강의일 때")
    void apply_already_applied_fail() {
        // given
        LectureApplyApiReqDto reqDto = new LectureApplyApiReqDto(1000L, 1L, 1L);

        List<LectureOption> optionList = new ArrayList<>();
        optionList.add(new LectureOption(1L, lecture, 30, 30, LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        lectureOptions = optionList;

        LectureHistory lectureHistory = new LectureHistory(1L, lecture, lectureOptions.get(0).getLectureOptionId(), 1000L, LocalDateTime.now(), null, null);
        when(lectureRepository.findById(anyLong())).thenReturn(lecture);
        when(lectureOptionRepository.findByLectureIdAndLectureOptionId(anyLong(), anyLong())).thenReturn(lectureOptions.get(0));
        when(lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(anyLong(), anyLong(), anyLong())).thenReturn(lectureHistory);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            lectureService.apply(reqDto);
        });

        // then
        assertEquals("이미 신청된 강의입니다.", exception.getMessage());
        verify(lectureHistoryRepository, never()).save(any());
    }

    @Test
    @DisplayName("강의 신청 완료 여부 조회 - true")
    void check_apply_true() {
        // given
        LectureHistory lectureHistory = new LectureHistory(1L, lecture, lectureOptions.get(0).getLectureOptionId(), 1000L, LocalDateTime.now(), null, null);
        when(lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(anyLong(), anyLong(), anyLong())).thenReturn(lectureHistory);

        // when
        boolean result = lectureService.checkApply(1000L, 1L, 1L);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("강의 신청 완료 여부 조회 - false")
    void check_apply_false() {
        // given
        when(lectureHistoryRepository.findByLectureIdAndLectureOptionIdAndUserId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        // when
        boolean result = lectureService.checkApply(1000L, 1L, 1L);

        //then
        assertFalse(result);
    }
}
