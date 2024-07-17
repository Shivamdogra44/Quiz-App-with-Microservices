package com.result.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.result.entities.Result;
import com.result.payload.ResultDto;
import com.result.repositories.ResultRepo;
@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResultDto createResult(ResultDto resultDto) {
       
        Result result=modelMapper.map(resultDto, Result.class);
        resultRepo.save(result);
        return modelMapper.map(result, ResultDto.class);
        
    }

    @Override
    public List<ResultDto> getResult() {
       List<Result> results=resultRepo.findAll();
       List<ResultDto> resultDtos= results.stream().map(result->modelMapper.map(result, ResultDto.class)).collect(Collectors.toList());
       return resultDtos;
    }

    @Override
    public ResultDto getResultById(long id) {
        Result result = resultRepo.findById(id).orElseThrow(()-> new RuntimeException("Result not found"));
        ResultDto resultDto=modelMapper.map(result, ResultDto.class);
        return resultDto;
    }

}
