package com.hhplus.hhplus02.myapplication.domain.dto;

import java.io.Serializable;

public record LectureApplyApiReqDto(
        // 회원 ID
        Long userId,
        // 특강 ID
        Long lectureId
) implements Serializable {
}
