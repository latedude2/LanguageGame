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

    //Strings for normal text (without hint texts) in the questions)
    private String[][] question = {
        {"Godmorgen Alex, can du come here for a second?"},//1
        {" was making ",  "but we are out of", " og ", ", can ",  "go get some?"},//2
        {"Here is ", " for ", " shopping, ", " won’t have ", " until ", " get back."},//3
        {"Det er en rigtig smuk dag i dag, og vejret er super dejligt; ikke?"},//4
        {"It is a really beautiful ", ", and ", " is very nice; right?"},//5
        {"Hej, hvad skulle det være?"},//6
        {"Hej, what will it be?"},//7
        {"Let me get it for ", ", that will be "},//8
        {" get ", " back, would ", " like a ","?"},//9
        {"Hej, hvad skulle det være?"},//10
        {"Sure, ", " coming up, anything else?"},//11
        {"Okay, that will be ", "."},//12
        {"Perfect, tak, how is ", "?"},//13
        {"Good to see ", " are learning some "},//14
        {"How much was the " ," og "},//15
        {"Great, can I have the ", "?"},//16
        {"Well then, let's have some ", " shall we?"}//17
    };

    //Strings for text with hint in the question
    private String[][] hint = {
            {""},//1
            {"Jeg", "morgenmad", "mælk", "brød", "du"},//2
            {"halvtreds kroner", "din", "jeg", "morgenmad", "du"},//3
            {""},//4
            {"dag", " i dag", "vejret"},//5
            {""},//6
            {""},//7
            {"dig", "ni og en halv", " kroner"},//8
            {"Du", "fyrre og en halv kroner", "du", "kvittering"},//9
            {""},//10
            {"et franskbrød"},//11
            {"femten kroner"},//12
            {"vejret", "i dag"},//13
            {"du", "ord"},//14
            {"brød", "mælk"},//15
            {"kvittering"},//16
            {"morgenmad"}//17
    };

    //already written text in the answer's sentence
    private String[][] answer = {
            {"Ja, jeg", " nu"},//1
            {"Tak, "},//2
            {""},//3 no answers needed for that one
            {", jeg ", " ikke så godt "},//4
            {"Ja, ", " føles som "},//5
            {""},//6 full answer
            {"Jeg skal ", " en karton ", ", tak"},//7
            {"Her får du ", " kroner"},//8
            {""},//9 full answer
            {" skal bruge et ",", tak"},//10
            {""},//11 full answer
            {""},//12 full answer
            {" er super dejligt "},//13
            {"Ja, jeg har ",  "med mange"},//14
            {"Det blev ", " for ", " og ", " for "},//15
            {"Her "},//16
            {"Ja, jeg er ved at være "}//17
    };

    //Strings for the answers shown on the buttons
    private String[][] allPossibleAnswers = {
            {"", "", "", "", "", ""},//1
            {"", "", "", "", "", ""},//2
            {"", "", "", "", "", ""},//3 no answers needed
            {"", "", "", "", "", ""},//4
            {"", "", "", "", "", ""},//5
            {"", "", "", "", "", ""},//6 full answer
            {"", "", "", "", "", ""},//7
            {"", "", "", "", "", ""},//8
            {"", "", "", "", "", ""},//9 full answer
            {"", "", "", "", "", ""},//10
            {"", "", "", "", "", ""},//11 full answer
            {"", "", "", "", "", ""},//12 full answer
            {"", "", "", "", "", ""},//13
            {"", "", "", "", "", ""},//14
            {"", "", "", "", "", ""},//15
            {"", "", "", "", "", ""},//16
            {"", "", "", "", "", ""}//17
    };

    private TextView[] questionTextView = new TextView[question.length];
    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct
    private GameObject[] UIObjects; //images shown after pressing the hintButton

    private FileRead file = new FileRead();

    public Exchange(TextView[] questionTextView, String[] answerText, OurButton[] hintButtons, GameObject[] UIObjects, int[] correctAnswers) {
        for (int i = 0; i < questionTextView.length; i++) {
            this.questionTextView[i] = questionTextView[i];
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