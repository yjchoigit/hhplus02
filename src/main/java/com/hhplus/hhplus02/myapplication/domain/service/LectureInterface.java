package com.hhplus.hhplus02.myapplication.domain.service;

import com.hhplus.hhplus02.myapplication.controllers.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCancelApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureCreateApiReqDto;
import com.hhplus.hhplus02.myapplication.controllers.dto.LectureListApiResDto;

import java.util.List;

public interface LectureInterface {

    boolean apply(LectureApplyApiReqDto reqDto);

    boolean checkApply(Long userId, Long lectureId, Long lectureOptionId);

    List<LectureListApiResDto> list();

    void cancel(LectureCancelApiReqDto reqDto);

    Long create(LectureCreateApiReqDto reqDto);
}
