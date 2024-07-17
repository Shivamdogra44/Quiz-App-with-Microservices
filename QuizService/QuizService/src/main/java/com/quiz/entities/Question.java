package com.quiz.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable,Comparable<Question> {

    private long questionId;

    private String question;

    private Long quizId;

    private String option1;
    
    private String option2;

    private String option3;

    private String option4;

   
    private String correctOption;

  
        
    

    @Override
    public int compareTo(Question o) {
        // TODO Auto-generated method stub
        return Integer.compare((int)this.questionId,(int)o.questionId);
    }
    
}