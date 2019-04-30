package com.example.test4;

import android.text.TextUtils;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

    private String[] allText;
    private String[] questionText;
    private String[] answerText;
    private String[] gapText;
    private String[] hintText;
    private String[] possibleAnswers;
    private int[] correctAnswers;

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
        questionText = readQuest(allText);

    }

    public String[] readAll()
    {
        String[] text;
        int line = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((bufferedReader.readLine()) != null) {
                text = new String[line];
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

    public String[] readQuest(String[] text)
    {

        return questionText;
    }

    public String[] getAnswerText() {
        return answerText;
    }

    public String[] getGapText() {
        return gapText;
    }

    public String[] getHintText() {
        return hintText;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public String[] getQuestionText() {
        return questionText;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }
}