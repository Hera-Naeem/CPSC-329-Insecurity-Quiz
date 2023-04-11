package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Quiz_Data_Management.Question;

public class NumericalQuestion extends Question {
    /*
    Public facing methods
     */
    public NumericalQuestion(String question, String questionHint, String answer, String answerFollowup) {
        this.question = question;
        this.answer = answer;
        this.options = null;
        this.questionHint = questionHint;
        this.answerFollowup = answerFollowup;
        this.questionType = QuestionTypes.Numerical;

    }
    @Override
    public boolean validateAnswer(String answer) {
        try {
            double parsedAnswer = Double.parseDouble(answer);
            // set the parsed answer as the correct answer
            this.answer = Double.toString(parsedAnswer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /*
    Internal methods
     */
}