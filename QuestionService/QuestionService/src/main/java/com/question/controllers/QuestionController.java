package com.question.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.entities.Question;
import com.question.payload.QuestionDto;
import com.question.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    
    @PostMapping
    public ResponseEntity<QuestionDto> create(@RequestBody QuestionDto question)
    {

        QuestionDto questionDto=questionService.create(question);
        return new ResponseEntity<QuestionDto>(questionDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAll(){

        List<QuestionDto> questionDto= questionService.get();
        return new ResponseEntity<List<QuestionDto>>(questionDto,HttpStatus.OK);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDto> getOne(@PathVariable Long questionId){

        return new ResponseEntity<QuestionDto>(questionService.getOne(questionId),HttpStatus.OK);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsOfQuiz(@PathVariable Long quizId)
    {
        return new ResponseEntity<List<QuestionDto>>( questionService.getQuestionsOfQuiz(quizId),HttpStatus.OK);
    }


}
