package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Quiz_Data_Management.Question;

public class MultipleChoiceQuestion extends Question {

    /*
    Public facing methods
     */
    protected String[] options;
    public MultipleChoiceQuestion(String question, String optionsStr, String questionHint, String answer, String answerFollowup) {
        this.question = question;
        this.answer = answer;
        this.options = optionsStr.split("\\|");
        this.questionHint = questionHint;
        this.answerFollowup = answerFollowup;
    }
    public String[] getOptions() {
        return this.options.clone();
    }
    @Override
    public boolean validateAnswer(String answer) {
        return answer.equals(this.answer);
    }
}

    /*
    Internal methods
     */

