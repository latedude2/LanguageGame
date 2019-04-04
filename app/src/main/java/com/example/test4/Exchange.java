package com.example.test4;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

class Exchange extends Instance {
    private TextView[] text = new TextView[10];
    private Button[] answerButtons = new Button[6]; //IDs of possible answers
    private Button[] hintButtons; //buttons for showing the hint images
    private int[] selectAnswers = new int[6]; //selected answers during the game play
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct
    private GameObject[] UIObjects; //images shown after pressing the hintButton

    public Exchange(TextView[] text, String[] answerText, Button[] hintButtons, GameObject[] UIObjects, int[] correctAnswers) {
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

    void checkAnswer(int[] selectAnswers) {
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i] == selectAnswers[i]) {
                /** excepting the answer and saying that you're a good boy **/
                System.out.println("good boy");
            } else {
                /** saying that you fucked up **/
                System.out.println("you fucked up");
            }
        }
    }

    /*public int checkHint(){
        int x; //the index of an image

        return x;
    }*/


    void showHint(int x) {
        UIObjects[x] = 
    }
}