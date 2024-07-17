package com.quiz.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.cache.Cache;

import com.payload.QuizDto;
import com.quiz.entities.Answer;
import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.entities.Result;
import com.quiz.repositories.QuizRepository;



@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionClient questionClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ResultClient resultClient;

    
   

  
    @Override
    public QuizDto add(QuizDto quizDto) {
        // TODO Auto-generated method stub
        
        Quiz quiz= this.modelMapper.map(quizDto, Quiz.class);
        Quiz qsave=this.quizRepository.save(quiz);
    
        return this.modelMapper.map(qsave, QuizDto.class);
    }

    @Override
    public List<QuizDto> get() {
        // TODO Auto-generated method stub
        List<Quiz> quizzes= quizRepository.findAll();
        List<Quiz> newQuizList=quizzes.stream().map(quiz->{
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        List<QuizDto> qList= newQuizList.stream().map(quiz->modelMapper.map(quiz, QuizDto.class))
        .collect(Collectors.toList());

        return qList;
    }

    @Override
@Cacheable(value = "QuizDto",key = "#id+'-'+#noOfQues")
    public QuizDto get(Long id, Long noOfQues) {
        // TODO Auto-generated method stub
    return createQuiz(id, noOfQues);
    }
    public QuizDto createQuiz(Long id, Long noOfQues)
    {
        Quiz quiz=quizRepository.findById(id).orElseThrow(()-> new RuntimeException("Quiz Not Found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(id));
        QuizDto quizDto=modelMapper.map(quiz, QuizDto.class);
        List<Question> l1= new LinkedList<>();
        List<Question> l2= quiz.getQuestions();
        int n =noOfQues.intValue();
        Random random = new Random();
        while (l1.size() < n && !l2.isEmpty()) {
            int randomIndex = random.nextInt(l2.size());
            l1.add(l2.remove(randomIndex));
        }
        quizDto.setQuestions(l1);
        return  quizDto;
    }


    public List<Answer> answers(List<Answer> answer,Long id, Long noOfQues)
    {

        QuizDto quizDto= getFromCache(id, noOfQues);
        Integer ans=storeResult(quizDto.getQuestions(),answer,id);
        return answer;


    }

   public QuizDto getFromCache(Long id, Long noOfQues) {
        Cache cache = cacheManager.getCache("QuizDto");
        if (cache != null) {
            String cacheKey = id + "-" + noOfQues;
            Cache.ValueWrapper wrapper = cache.get(cacheKey);
            if (wrapper != null) {
                evictAllCacheValues("QuizDto");;
                return (QuizDto) wrapper.get();
            }
        }
        return null; // Handle the case when the cache is empty
    }
    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }

    Integer storeResult(List<Question> questions,List<Answer>answers,Long id)
    {
        Collections.sort(questions);
        Collections.sort(answers);
        int total=questions.size();
        int cor=0;
        for(int i=0;i<answers.size();++i)
        {
            if (questions.get(i).getCorrectOption()==null) {
                return (int)questions.get(i).getQuestionId();
            }
            else if(questions.get(i).getCorrectOption().equalsIgnoreCase(answers.get(i).getAnswer()))
            cor++;
        }
        savResult(questions.get(0).getQuizId(),total , cor);
        return cor;
        
    }

    Result savResult(long quizId,int totalQuestion, int totalCorrectAnswer)
    {
        Result result= Result.builder().quizId(quizId).totalQuestions(totalQuestion).correctAnswer(totalCorrectAnswer).build();
        Result savedResult=resultClient.createResult(result);
        return savedResult;
    }




}
