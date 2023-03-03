package org.insecurity_quiz.Quiz_Data_Management;



public abstract class Question {
    public enum QuestionTypes {
        MultipleChoice, ShortAnswer, SelectCorrect, Numerical;
    }

    protected String question;
    protected String answer;
    protected String questionHint;
    protected String answerFollowup;

    public QuestionTypes getQuestionType() {
        return QuestionTypes.Numerical;
    }

    public String getQuestion() {
        return this.question;
    }

    public abstract boolean validateAnswer(String answer);

    public String getQuestionHint() {
        return this.questionHint;
    }

    public String getAnswerFollowup() {
        return this.answerFollowup;
    }
}
