package com.result.service;

import java.util.List;

import com.result.payload.ResultDto;

public interface ResultService {

     ResultDto createResult(ResultDto resultDto);

     List<ResultDto> getResult();

     ResultDto getResultById(long id);


}
