package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Quiz_Data_Management.Question;

public class ShortAnswerQuestion extends Question {
    /*
    Public facing methods
     */
    public ShortAnswerQuestion(String question, String questionHint, String answer, String answerFollowup) {
        this.question = question;
        this.questionHint = questionHint;
        this.answer = answer;
        this.answerFollowup = answerFollowup;
    }
    @Override
    public boolean validateAnswer(String answer) {
        return true;
    }
    /*
    Internal methods
     */
}
