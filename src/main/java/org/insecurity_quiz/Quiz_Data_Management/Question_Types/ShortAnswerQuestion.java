package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Question;

public class ShortAnswerQuestion extends Question {
    /*
    Public facing methods
     */
    public ShortAnswerQuestion(String question, String[] options, String answer) {
        this.question = question;
        this.answer = answer;
    }
    @Override
    public boolean validateAnswer(String answer) {
        return true;
    }
    /*
    Internal methods
     */
}
