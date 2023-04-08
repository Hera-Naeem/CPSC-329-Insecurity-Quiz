package org.insecurity_quiz.Quiz_Data_Management;



public abstract class Question {

    public enum QuestionTypes {
        MultipleChoice, ShortAnswer, SelectCorrect, Numerical;
    }

    protected String question;
    protected String answer;

    protected String[] options;


    public QuestionTypes getQuestionType() {
        return QuestionTypes.Numerical;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getOptions(){
        return this.options;
    }

    public String getAnswer() { return this.answer;}




    public abstract boolean validateAnswer(String answer);
}
