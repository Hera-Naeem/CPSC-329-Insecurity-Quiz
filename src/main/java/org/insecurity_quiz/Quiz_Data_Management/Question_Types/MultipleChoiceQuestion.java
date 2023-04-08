package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import java.util.ArrayList;

import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;
import org.insecurity_quiz.Question;

public class MultipleChoiceQuestion extends Question {


    public MultipleChoiceQuestion(String question, String[] options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }


    @Override
    public boolean validateAnswer(String answer) {
        return this.answer.equals(answer);
    }

    public String getOptionOne() {
        return options[0];
    }

    public String getOptionTwo() {
        return options[1];
    }

    public String getOptionThree() {
        return options[2];
    }

    public String getOptionFour() {return options[3];}

}
