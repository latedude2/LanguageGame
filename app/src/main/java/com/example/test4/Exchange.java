package com.example.test4;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Exchange extends Instance {



    private GameObject[] UIObjects; //images shown after pressing the hintButton


    private StringBuffer questionText; //array for normal text of other character
    private String[] hintText; //array for text with image hints
    private String[] answerText; //array for already written text of answer
    private String[] gapText; // array for gaps in the answer text
    private String[] answers; //array of all 6 possible answers

    MainActivity mainActivity = new MainActivity();
    FileRead fileRead = new FileRead();

    private ArrayList<Integer> wordIndexList = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();

    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct

    int n = 0;

    //private TextView[] questionTextView = new TextView[questionText.length];

    public Exchange(String[] answerText, StringBuffer questionText, String[] hintText, String[] answers, String[] gapText, int[] correctAnswers) {
            this.questionText = questionText;
            this.answerText = answerText;
            this.hintText = hintText;
            this.answers = answers;
            this.gapText = gapText;
            this.correctAnswers = correctAnswers;
    }

    //activated in the OnClickListener or so
    public void clickAnswer(TextView answer) //  the method requires the textView which was clicked
    {
        for (int i=0; i<gapText.length; i++){
            if (gapText[i] != null){
                gapText[i] = answer.getText().toString();
            }
        }
    }

    public SpannableString clickHint(){
        StringBuffer stringBuffer = questionText;
        final Matcher matcher = Pattern.compile("#\\s*(\\w+)").matcher(stringBuffer);
        while (matcher.find()){
            final String word = matcher.group(1);
            int wordIndex = stringBuffer.indexOf(word) - 1;
            wordIndexList.add(wordIndex);
            wordList.add(word);
        }
        for (int i = 0; i < wordIndexList.size(); i++){
            //StringBuilder stringBuilder = new StringBuilder(stringBuffer);
            stringBuffer.deleteCharAt(wordIndexList.get(i) - i);
            //String resultString = stringBuilder.toString();
            //questionText = resultString;
        }
        SpannableString spannableString = new SpannableString(stringBuffer);
        for (int i = 0; i < wordIndexList.size(); i++){
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    //  mainActivity.getHintImage();
                    new CountDownTimer(10000, 1000){
                        public void onTick(long milliUntilFinished){
                            //addfunction
                        }
                        public void onFinish(){
                            //mainActivity.getHintImage().setImageResource(0);
                        }
                    }.start();
                }
            };
            spannableString.setSpan(clickableSpan, wordIndexList.get(i) - i, wordIndexList.get(i) - i + wordList.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
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