package com.quiz.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quiz.entities.Result;

@FeignClient(name="RESULT")
public interface ResultClient {


    @PostMapping("/result")
    Result createResult(@RequestBody Result result);
    
}
