package com.quiz.services;

import java.util.*;

import com.payload.QuizDto;
import com.quiz.entities.Answer;
import com.quiz.entities.Quiz;

public interface QuizService {

    QuizDto add(QuizDto quiz);
    List<QuizDto> get();
    QuizDto get(Long id,Long noOfQues);
    List<Answer> answers(List<Answer> answer,Long id, Long noOfQues);
} 
