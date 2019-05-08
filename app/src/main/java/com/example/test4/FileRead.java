package com.example.test4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

    private StringBuffer[] allText; //all the text in the file
    private StringBuffer questionText; //normal text of other character
    public StringBuffer answerText;
    private StringBuffer[] allAnswers; //array of all 6 possible answers
    private int[] correctAnswers = new int[6];

    private String text;

    private InputStream inputStream;

    public FileRead (InputStream inputStream){
        this.inputStream = inputStream;
    }

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

    public char[][] readStructureChars(){
        StringBuffer lines = new StringBuffer();

        int lineIndex = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((text = bufferedReader.readLine()) != null) {
                line = new StringBuffer();
                line.append(text);
                lineIndex++;
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[][] structure = new char[lines.length][lines.toString().length()];
        for(int i=0; i<lines.length; i++){
            for(int ii=0; ii<lines.toString().length(); ii++){
                structure[i][ii] = lines[i].charAt(ii);
            }
        }
        return structure;
    }
}