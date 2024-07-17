package com.quiz.controllers;

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

import com.payload.QuizDto;
import com.quiz.entities.Answer;
import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    
    @PostMapping
    public ResponseEntity<QuizDto> create(@RequestBody QuizDto quiz)
    {
       QuizDto qDto= quizService.add(quiz);
        return new ResponseEntity<QuizDto>(qDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> get()
    {
        return new ResponseEntity<List<QuizDto>>(quizService.get(),HttpStatus.OK);
    }

    @GetMapping("/{id}/noOfQues/{noOfQues}")
    public ResponseEntity<QuizDto> getOne(@PathVariable Long id, @PathVariable Long noOfQues)
    {
        return new ResponseEntity<QuizDto>(quizService.get(id,noOfQues),HttpStatus.OK);
    }

    @PostMapping("/answer/{id}/noOfQues/{noOfQues}")
    public ResponseEntity<List<Answer>> answers(@RequestBody List<Answer> answer,@PathVariable Long id, @PathVariable Long noOfQues)
    {
        return new ResponseEntity<List<Answer>>(quizService.answers(answer,id,noOfQues),HttpStatus.CREATED);
    }

}
