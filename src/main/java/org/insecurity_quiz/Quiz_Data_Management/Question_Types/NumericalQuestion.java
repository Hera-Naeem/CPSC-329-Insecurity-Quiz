package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Quiz_Data_Management.Question;

public class NumericalQuestion extends Question {
    /*
    Public facing methods
     */
    public NumericalQuestion(String question, String questionHint, String answer, String answerFollowup) {
        this.question = question;
        this.questionHint = questionHint;
        this.answer = answer;
        this.answerFollowup = answerFollowup;
    }
    @Override
    public QuestionTypes getQuestionType() {
        return QuestionTypes.Numerical;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public boolean validateAnswer(String answer) {
        return false;
    }

    @Override
    public String getQuestionHint() {
        return this.questionHint;
    }

    @Override
    public String getAnswerFollowup() {
        return this.answerFollowup;
    }

    /*
    Internal methods
     */
}
