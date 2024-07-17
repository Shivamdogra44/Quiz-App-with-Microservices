package com.question.payload;

import lombok.Data;

@Data
public class QuestionDto {
    private Long questionId;

    private String question;

    private Long quizId;

    private String option1;
    
    private String option2;

    private String option3;

    private String option4;

    private String correctOption;
    
}
