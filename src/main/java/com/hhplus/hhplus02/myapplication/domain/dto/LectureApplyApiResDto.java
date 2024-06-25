package com.hhplus.hhplus02.myapplication.domain.dto;

import java.io.Serializable;

public record LectureApplyApiResDto(
        // 회원 ID
        Long userId,
        // 특강 ID
        Long lectureId,
        // 신청 성공 여부
        boolean successYn
) implements Serializable {
}
