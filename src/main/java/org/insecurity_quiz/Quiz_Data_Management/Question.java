package org.insecurity_quiz.Quiz_Data_Management;



public abstract class Question {
    public enum QuestionTypes {
        MultipleChoice, ShortAnswer, SelectCorrect, Numerical;
    }

    protected String question;
    protected String answer;
    protected String questionHint;
    protected String answerFollowup;

    public abstract QuestionTypes getQuestionType();

    public abstract String getQuestion();

    public abstract boolean validateAnswer(String answer);

    public abstract String getQuestionHint();

    public abstract String getAnswerFollowup();
}
