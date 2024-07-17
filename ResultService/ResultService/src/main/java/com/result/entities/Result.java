package com.result.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Result {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private long quizId;

    private int correctAnswer;

    private int totalQuestions;

}
