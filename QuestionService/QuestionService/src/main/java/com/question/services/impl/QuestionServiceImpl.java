package com.question.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;
import com.question.entities.Question;
import com.question.payload.QuestionDto;
import com.question.repositories.QuestionRepository;
import com.question.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{


    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public QuestionDto create(QuestionDto questionDto) {
        // TODO Auto-generated method stub
        Question question= modelMapper.map(questionDto, Question.class);

       questionRepository.save(question);
       return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> get() {
        // TODO Auto-generated method stub
        List<Question> questions=questionRepository.findAll();
        List<QuestionDto> questionDto=questions.stream().map(que->modelMapper.map(que, QuestionDto.class)).collect(Collectors.toList());
       return questionDto;
    }

    @Override
    public QuestionDto getOne(Long id) {
        // TODO Auto-generated method stub
        Question question=questionRepository.findById(id).orElseThrow(()-> new RuntimeException("Question Not Found"));
        QuestionDto questionDto=modelMapper.map(question, QuestionDto.class);
        return questionDto;
    }

    @Override
    public List<QuestionDto> getQuestionsOfQuiz(Long quizId) {
        // TODO Auto-generated method stub
        List<Question> questions=questionRepository.findByQuizId(quizId);
        List<QuestionDto> questionDtos=questions.stream().map(que->modelMapper.map(que, QuestionDto.class))
        .collect(Collectors.toList());
        return questionDtos;
    }

}
