package org.insecurity_quiz.Quiz_Data_Management.Question_Types;

import org.insecurity_quiz.Question;

import java.util.Arrays;
import java.util.List;

public class SelectCorrectQuestion extends Question {
    /*
    Constants
     */
    public static String SEPARATOR = ",";

    /*
    Public facing methods
     */
    public SelectCorrectQuestion(String question, String[] options, String annswer) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }
    //Note: Assumption is that there are no repeats in answer or input.
    @Override
    public boolean validateAnswer(String answer) {
        String[] answers = this.answer.split(SEPARATOR);
        List<String> answersArray = Arrays.asList(answers);
        String[] inputs = answer.split(SEPARATOR);

        int numAnswers = answers.length;
        int numMatch = 0;
        for (String input:inputs) {
            boolean match = answersArray.contains(input);
            if (match) {
                numMatch += 1;
            }
        }

        return numMatch == numAnswers;
    }
    /*
    Internal methods
     */
}
