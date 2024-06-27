package com.hhplus.hhplus02.myapplication.controllers;

import com.hhplus.hhplus02.myapplication.controllers.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCancelApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCreateApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureListApiResDto;
import com.hhplus.hhplus02.myapplication.domain.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 강의 신청
    @PostMapping(value = "/lectures/apply")
    public boolean apply(@RequestBody LectureApplyApiReqDto reqDto) {
        return lectureService.apply(reqDto);
    }

    // 강의 신청 완료 여부 조회
    @GetMapping(value = "/lectures/apply/check")
    public boolean checkApply(@RequestParam(name = "userId") Long userId,
                                                 @RequestParam(name = "lectureId") Long lectureId,
                                                 @RequestParam(name = "lectureOptionId") Long lectureOptionId) {
        return lectureService.checkApply(userId, lectureId, lectureOptionId);
    }

    // 강의 취소
    @PatchMapping(value = "/lectures/cancel")
    public void cancel(@RequestBody LectureCancelApiReqDto reqDto) {
        lectureService.cancel(reqDto);
    }

    // 강의 목록 조회
    @GetMapping(value = "/lectures/list")
    public List<LectureListApiResDto> list() {
        return lectureService.list();
    }

    // 강의 등록
    @PostMapping(value = "/lectures/create")
    public Long create(@RequestBody LectureCreateApiReqDto reqDto) {
        return lectureService.create(reqDto);
    }

    // TODO 강의 수정

    // TODO 특정 강의 옵션 등록

    // TODO 특정 강의 옵션 수정

    // TODO 특정 강의 옵션 삭제


}
