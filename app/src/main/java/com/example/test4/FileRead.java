package com.example.test4;

import android.text.TextUtils;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

    private String[] allText; //all the text in the file
    private String[] questionText; //array for normal text of other character
    private String[] hintText; //array for text with image hints
    private String[] answerText; //array for already written text of answer
    private String[] gapText; //array for gaps in the answer text
    private String[] allAnswers; //array of all 6 possible answers
    private int[] correctAnswers; //all of the possible answers

    private int i = 0;

    private InputStream inputStream;
    private StringBuffer stringBuffer = new StringBuffer();

    private TextUtils.SimpleStringSplitter hintSplitter = new TextUtils.SimpleStringSplitter('#');
    private TextUtils.SimpleStringSplitter gapSplitter = new TextUtils.SimpleStringSplitter('&');

    public FileRead (int index, InputStream inputStream){
        this.i = index;
        this.inputStream = inputStream;
    }

    public void read() {
        allText = readAll();
        //questionText = readQuest(allText);

    }

    //reads all of the file and puts every line in the array of Strings to be put in specific arrays of Strings in other methods
    public String[] readAll()
    {
        String[] text = new String[8];
        int line = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((bufferedReader.readLine()) != null) {
                stringBuffer.append(text[line]);
                line++;
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /*public String[] readQuest(String[] text)
    {
        //to be updated
        return question;
    }*/
    /*public String[] readAnswer(String[] text)
    {
        //to be updated
        return answer;
    }*/
    /*public String[] readAllAnswers(String[] text)
    {
        //to be updated
        return answers;
    }*/

    public String[] getAnswerText() {
        return answerText;
    }

    public String[] getGapText() {
        return gapText;
    }

    public String[] getHintText() {
        return hintText;
    }

    public String[] getAllAnswers() {
        return allAnswers;
    }

    public String[] getQuestionText() {
        return questionText;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }
}