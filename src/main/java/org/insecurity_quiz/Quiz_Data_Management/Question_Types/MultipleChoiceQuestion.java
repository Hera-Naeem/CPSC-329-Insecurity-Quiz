package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Question;

public class MultipleChoiceQuestion extends Question {

    /*
    Public facing methods
     */
    public MultipleChoiceQuestion(String question, String choices, String questionHint, String answer, String answerFollowup) {
        this.question = question;
        this.questionHint = questionHint;
        this.answer = answer;
        this.answerFollowup = answerFollowup;
    }
    @Override
    public boolean validateAnswer(String answer) {
        return answer.equals(this.answer);
    }

    /*
    Internal methods
     */

}
