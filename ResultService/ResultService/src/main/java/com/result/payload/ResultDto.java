package com.result.payload;

import lombok.Data;

@Data
public class ResultDto {
    private long id;
    
    private long quizId;

    private int correctAnswer;

    private int totalQuestions;
}
