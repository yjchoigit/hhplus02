package com.hhplus.hhplus02.myapplication.application.controllers;

import com.hhplus.hhplus02.myapplication.infrastructure.LectureInterface;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureCheckApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureListApiResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureInterface lectureInterface;

    // 특강 신청
    @PostMapping(value = "/lectures/apply")
    public LectureApplyApiResDto apply(@RequestBody LectureApplyApiReqDto reqDto) {
        return lectureInterface.apply(reqDto);
    }

    // 특강 신청 완료 여부 조회
    @GetMapping(value = "/lectures/apply/{userId}")
    public LectureCheckApplyApiResDto checkApply(@PathVariable(name = "userId") Long userId, @RequestParam(name = "lectureId") Long lectureId) {
        return lectureInterface.checkApply(userId, lectureId);
    }

    // 특강 목록 조회
    @GetMapping(value = "/lectures/list")
    public List<LectureListApiResDto> list() {
        return lectureInterface.list();
    }
}
