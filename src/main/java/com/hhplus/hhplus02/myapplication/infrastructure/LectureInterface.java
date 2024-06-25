package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiReqDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureCheckApplyApiResDto;
import com.hhplus.hhplus02.myapplication.domain.dto.LectureListApiResDto;

import java.util.List;

public interface LectureInterface {

    LectureApplyApiResDto apply(LectureApplyApiReqDto reqDto);

    LectureCheckApplyApiResDto checkApply(Long userId, Long lectureId);

    List<LectureListApiResDto> list();
}
