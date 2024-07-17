package com.quiz.entities;

import lombok.Data;

@Data
public class Answer implements Comparable<Answer> {

    private long questionId;
    private String answer;
    @Override
    public int compareTo(Answer o) {
        // TODO Auto-generated method stub
        return Integer.compare((int)this.questionId, (int)o.questionId);
    }

}
