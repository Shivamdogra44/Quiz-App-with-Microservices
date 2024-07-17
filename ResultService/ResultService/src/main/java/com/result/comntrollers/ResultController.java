package com.result.comntrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.result.payload.ResultDto;
import com.result.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
   private ResultService resultService;

   @PostMapping
   public ResponseEntity<ResultDto> createResult(@RequestBody ResultDto resultDto){

    return new ResponseEntity<ResultDto>(resultService.createResult(resultDto),HttpStatus.CREATED); 
   }

   @GetMapping
   public ResponseEntity<List<ResultDto>> getResult(){

    return new ResponseEntity<List<ResultDto>>(resultService.getResult(),HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ResultDto> getById(@PathVariable long id)
   {
    return new ResponseEntity<>(resultService.getResultById(id),HttpStatus.OK);
   }

}
