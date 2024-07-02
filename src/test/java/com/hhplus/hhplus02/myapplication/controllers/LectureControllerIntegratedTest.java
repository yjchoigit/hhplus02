package com.hhplus.hhplus02.myapplication.controllers;

import com.hhplus.hhplus02.myapplication.Setting;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCancelApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureListApiResDto;
import com.hhplus.hhplus02.myapplication.domain.entities.Lecture;
import com.hhplus.hhplus02.myapplication.fixture.LectureFixture;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LectureControllerIntegratedTest extends Setting {

    private static final String PATH = "/lectures";

    @Autowired
    private LectureFixture lectureFixture;

    @Test
    @DisplayName("강의 신청 - 최대 신청 인원 수가 30명인 특정 강의를 40명이 동시에 신청했을 때 10명은 실패")
    void apply_concurrent() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 0);
        Long lectureOptionId = 1L;
        int totalUsers = 40;
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        Random random = new Random();
        // when
        for (int i = 1; i <= totalUsers; i++) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                ExtractableResponse<Response> response = post(PATH + "/apply", new LectureApplyApiReqDto(random.nextLong(), lecture.getLectureId(), lectureOptionId));
                return response.statusCode() == HttpStatus.OK.value();
            });
            futures.add(future);
        }

        // then
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));
        allOf.join();

        long failUsers = futures.stream()
                .map(CompletableFuture::join)
                .filter(f -> f.booleanValue() == Boolean.FALSE)
                .count();

        assertThat(failUsers).isEqualTo(10);
    }

    @Test
    @DisplayName("강의 신청 성공 - 최대 신청 인원 수가 30명인 특정 강의를 30명이 동시에 신청했을 때")
    void apply_concurrent_success() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 0);
        Long lectureOptionId = 1L;
        int totalUsers = 30;
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        Random random = new Random();
        // when
        for (int i = 1; i <= totalUsers; i++) {
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                ExtractableResponse<Response> response = post(PATH + "/apply", new LectureApplyApiReqDto(random.nextLong(), lecture.getLectureId(), lectureOptionId));
                return response.statusCode() == HttpStatus.OK.value();
            });
            futures.add(future);
        }

        // then
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));
        allOf.join();

        long failUsers = futures.stream()
                .map(CompletableFuture::join)
                .filter(f -> f.booleanValue() == Boolean.FALSE)
                .count();

        assertThat(failUsers).isZero();
    }

    @Test
    @DisplayName("강의 신청 완료 여부 조회 - true")
    void check_apply_true() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 10);
        lectureFixture.강의_신청내역_등록(lecture);

        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("userId", 1000L);
        parametersMap.put("lectureId", 1L);
        parametersMap.put("lectureOptionId", 1L);

        // when
        ExtractableResponse<Response> response = get(PATH + "/apply/check", parametersMap);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEquals(response.jsonPath().get(), true);
    }

    @Test
    @DisplayName("강의 신청 완료 여부 조회 - false")
    void check_apply_false() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 10);
        lectureFixture.강의_신청내역_등록(lecture);

        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("userId", 1001L);
        parametersMap.put("lectureId", 1L);
        parametersMap.put("lectureOptionId", 1L);

        // when
        ExtractableResponse<Response> response = get(PATH + "/apply/check", parametersMap);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEquals(response.jsonPath().get(), false);
    }

    @Test
    @DisplayName("강의 취소 성공")
    void cancel_success() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 10);
        lectureFixture.강의_신청내역_등록(lecture);
        LectureCancelApiReqDto reqDto = new LectureCancelApiReqDto(1000L, 1L, 1L);

        // when
        ExtractableResponse<Response> response = patch(PATH + "/cancel", reqDto);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("강의 취소 실패 - 강의 신청 내역이 없으면")
    void cancel_no_history_fail() {
        // given
        Lecture lecture = lectureFixture.강의_등록(30, 0);
        lectureFixture.강의_신청내역_등록(lecture);
        LectureCancelApiReqDto reqDto = new LectureCancelApiReqDto(1000L, 1L, 1L);

        // when
        ExtractableResponse<Response> response = patch(PATH + "/cancel", reqDto);

        // then
        assertThat(response.statusCode()).isNotEqualTo(HttpStatus.OK.value());
    }
    
    @Test
    @DisplayName("강의 목록 조회")
    void list() {
        // given
        lectureFixture.강의_등록(30, 0);

        // when
        ExtractableResponse<Response> response = get(PATH + "/list");

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<LectureListApiResDto> lectureList = response.jsonPath().getList(".", LectureListApiResDto.class);
        assertThat(lectureList).isNotEmpty();
    }
}