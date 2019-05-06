package com.example.test4;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {

    private StringBuffer[] allText; //all the text in the file
    private StringBuffer questionText; //array for normal text of other character
    private String[] hintText; //array for text with image hints
    private StringBuffer answerText; //array for already written text of answer
    private String[] gapText; //array for gaps in the answer text
    private String[] allAnswers; //array of all 6 possible answers
    private int[] correctAnswers; //all of the possible answers
    private ArrayList<Integer> wordIndexList = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();

    private int index = 0;
    private String text;

    private InputStream inputStream;
    private StringBuffer stringBuffer = new StringBuffer();

    //private TextUtils.SimpleStringSplitter hintSplitter = new TextUtils.SimpleStringSplitter('#');
    //private TextUtils.SimpleStringSplitter gapSplitter = new TextUtils.SimpleStringSplitter('&');

    public FileRead (int index, InputStream inputStream){
        this.index = index;
        this.inputStream = inputStream;
    }

    public FileRead(){}

    public void read() {
        allText = readAll();
        questionText = readQuest(this.allText);
        answerText = readAnswer(this.allText);
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
    /*public String[] readAnswer(String[] text)
    {
        //to be updated
        return answer;
    }*/
    /*public String[] readAllAnswers(String[] text)
    {   this.answers = new String[6];
        for (int i = 0; i < answers.length; i++){
            answers[i] = text [i + 2];
        }
        return answers;
    }*/

    public StringBuffer getAnswerText() {
        return answerText;
    }

    public String[] getGapText() {
        return gapText;
    }

    public String[] getAllAnswers() {
        return allAnswers;
    }

    public StringBuffer getQuestionText() {
        return questionText;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }
}