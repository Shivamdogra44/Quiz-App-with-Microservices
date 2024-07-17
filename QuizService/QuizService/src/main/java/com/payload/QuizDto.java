package com.payload;

import java.io.Serializable;
import java.util.List;

import com.quiz.entities.Question;

import lombok.Data;

@Data
public class QuizDto implements Serializable {
    
private Long id;

    private String title;

     private List<Question> questions;
}
