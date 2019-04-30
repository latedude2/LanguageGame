package com.example.test4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

class Exchange extends Instance {


    private GameObject[] UIObjects; //images shown after pressing the hintButton

    private String[] questionText; //array for normal text of other character
    private String[] hintText; //array for text with image hints
    private String[] answerText; //array for already written text of answer
    private String[] gapText; // array for gaps in the answer text
    private String[] answers; //array of all 6 possible answers

    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct

    int n = 0;

    //private TextView[] questionTextView = new TextView[questionText.length];

    public Exchange(String[] answerText, String[] questionText, String[] hintText, String[] answers, String[] gapText, int[] correctAnswers) {
            this.questionText = questionText;
            this.answerText = answerText;
            this.hintText = hintText;
            this.answers = answers;
            this.gapText = gapText;
            this.correctAnswers = correctAnswers;
    }

    public void clickAnswer(TextView answer) //  teh method requires the textView which was clicked
    {
        for (int i=0; i<gapText.length; i++){
            if (gapText[i] != null){
                gapText[i] = answer.getText().toString();
            }
        }
    }

    public void checkAnswer()
    {

    }

    /*public int checkHint(){
        int x; //the index of an image

        return x;
    }*/


    /*void showHint(int x) {

    }*/
}