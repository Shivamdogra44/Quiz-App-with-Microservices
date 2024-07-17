package com.quiz.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    private long id;
    
    private long quizId;

    private int correctAnswer;

    private int totalQuestions;

}
