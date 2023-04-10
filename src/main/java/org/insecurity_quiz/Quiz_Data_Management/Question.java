package org.insecurity_quiz.Quiz_Data_Management;



public abstract class Question {



    public enum QuestionTypes {
        MultipleChoice, ShortAnswer, SelectCorrect, Numerical;
    }

    protected String question;
    protected String answer;
    protected String questionHint;
    protected String answerFollowup;
    protected String[] options;
    protected QuestionTypes questionType;



    public QuestionTypes getQuestionType() {
        return this.questionType;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getOptions(){
        return this.options;
    }

    public String getAnswer() { return this.answer;}

    public String getQuestionHint() {
        return this.questionHint;
    }

    public String getAnswerFollowup() {
        return this.answerFollowup;
    }


    public abstract boolean validateAnswer(String answer);
}
