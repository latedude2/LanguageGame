package com.example.test4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

class Exchange extends Instance {
    private OurButton[] answerPos; //gaps for the answers
    private OurButton[] answerButtons = new OurButton[2]; //IDs of possible answers
    private OurButton[] hintButtons; //buttons for showing the hint images


    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct
    private GameObject[] UIObjects; //images shown after pressing the hintButton

    String[] questionText;
    String[] answerText;
    String[] hintText;
    String[] answers;
    String[] gapText;

    FileRead file;
    int n = 0;

    //private TextView[] questionTextView = new TextView[questionText.length];

    public Exchange(FileRead file, String[] answerText, String[] questionText, String[] hintText, String[] answers, String[] gapText, int[] correctAnswers) {
        this.file = file;
        if (this.file != null) {
            this.questionText = questionText;
            this.answerText = answerText;
            this.hintText = hintText;
            this.answers = answers;
            this.gapText = gapText;
            this.correctAnswers = correctAnswers;
        }
    }

    public void clickAnswer(OurButton answer){
        for (int i = 0; i < answerPos.length; i++)
            if (answerPos[i].getText() != null) {
                answerPos[i].setText(answer.getText());
                break;
            }
    }

    void checkAnswer(OurButton[] answerPos) {
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i] == answerPos[i].getIndex()) {
                // excepting the answer and saying that you're a good boy
                System.out.println("good boy");
            } else {
                // saying that you fucked up
                System.out.println("you fucked up");
            }
        }


    }

    /*public int checkHint(){
        int x; //the index of an image

        return x;
    }*/


    /*void showHint(int x) {
        UIObjects[x] =
    }*/
}