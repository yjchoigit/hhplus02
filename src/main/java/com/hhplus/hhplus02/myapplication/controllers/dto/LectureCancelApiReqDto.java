package com.hhplus.hhplus02.myapplication.controllers.dto;

import java.io.Serializable;

public record LectureCancelApiReqDto(
        // 회원 ID
        Long userId,
        // 강의 ID
        Long lectureId,
        // 강의 옵션 ID
        Long lectureOptionId
) implements Serializable {
}
