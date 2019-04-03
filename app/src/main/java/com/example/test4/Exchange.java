package com.example.test4;

import android.widget.Button;
import android.widget.TextView;

class Exchange extends Instance{
    private int numberAnswers = 6;
    private TextView[] text = new TextView[10];
    private Button[] answerButtons = new Button[6]; /** IDs of possible answers **/
    private Button[] hintButtons; /** buttons for showing the hint images **/
    private int[] correctAnswers = new int[6]; /** answers that are possible to be correct  **/
    private UIElement[] hintImages; /** images shown after pressing the hintButton**/

    public Exchange(TextView[] text, String[] answerText, String[] hintButtons, UIElement[] hintImages, int[] correctAnswers){

        for (int i=0;i<numberAnswers;i++){
                this.text[i] = text[i];
                this.answerButtons[i].setText(answerText[i]);

        }

    }



    void checkAnswer(){

    }

    void showHint(){

    }
}
