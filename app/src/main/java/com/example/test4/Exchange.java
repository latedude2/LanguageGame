package com.example.test4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

class Exchange extends Instance {
    private OurButton[] answerPos;
    private OurButton[] answerButtons = new OurButton[2]; //IDs of possible answers
    private OurButton[] hintButtons; //buttons for showing the hint images
    private String sentence = "Okay, " + answerButtons[0].getText() + "kan " + answerButtons[1].getText() + "finde dem?";
    private TextView[] text = new TextView[5];
    private int[] selectAnswers = new int[6]; //selected answers during the game play
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct
    private GameObject[] UIObjects; //images shown after pressing the hintButton

    public Exchange(TextView[] text, String[] answerText, OurButton[] hintButtons, GameObject[] UIObjects, int[] correctAnswers) {
        for (int i = 0; i < text.length; i++) {
            this.text[i] = text[i];
        }
        for (int i = 0; i < answerButtons.length; i++) {
            this.answerButtons[i].setText(answerText[i]);
        }
        for (int i = 0; i < hintButtons.length; i++) {
            this.hintButtons[i] = hintButtons[i];
            this.UIObjects[i] = UIObjects[i];
        }
        for (int i = 0; i < correctAnswers.length; i++) {
            this.correctAnswers[i] = correctAnswers[i];
        }
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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