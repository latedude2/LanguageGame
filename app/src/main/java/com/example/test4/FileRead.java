package com.example.test4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

    private StringBuffer[] allText; //all the text in the file
    private StringBuffer questionText; //array for normal text of other character
    private StringBuffer answerText; //array for already written text of answer
    private StringBuffer[] allAnswers; //array of all 6 possible answers
    private int[] correctAnswers = new int[6];

    private String text;

    private InputStream inputStream;

    public FileRead (InputStream inputStream){
        this.inputStream = inputStream;
    }

    public FileRead(){}

    public void read() {
        allText = readAll();
        questionText = readQuest(this.allText);
        answerText = readAnswer(this.allText);
        allAnswers = readAllAnswers(this.allText);
    }

    //reads all of the file and puts every line in the array of Strings to be put in specific arrays of Strings in other methods
    public StringBuffer[] readAll()
    {
        StringBuffer[] lines = new StringBuffer[8];
        int lineIndex = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((text = bufferedReader.readLine()) != null) {
                lines[lineIndex] = new StringBuffer();
                lines[lineIndex].append(text);
                lineIndex++;
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public StringBuffer readAnswer(StringBuffer[] text)
    {
        StringBuffer answer = text[1];
        return answer;
    }


    public StringBuffer readQuest(StringBuffer[] text)
    {
        StringBuffer question = text[0];
        return question;
    }

    public StringBuffer[] readAllAnswers(StringBuffer[] text)
    {
        StringBuffer[] answers = new StringBuffer[6];
        for (int i = 2; i < text.length; i++){
            answers[i-2] = text[i];
        }
        return answers;
    }

    public StringBuffer getAnswerText() {
        return answerText;
    }


    public StringBuffer[] getAllAnswers() {
        return allAnswers;
    }

    public StringBuffer getQuestionText() {
        return questionText;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }
}