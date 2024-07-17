package com.question.services;

import java.util.List;

import com.question.entities.Question;
import com.question.payload.QuestionDto;


public interface QuestionService {

    QuestionDto create(QuestionDto question);

    List<QuestionDto> get();

    QuestionDto getOne(Long id);

    List<QuestionDto> getQuestionsOfQuiz(Long quizId);

}
