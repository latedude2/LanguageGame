package com.example.test4;

import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

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
    private int wordCount = 0;

    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers = new int[6]; //answers that are possible to be correct

    int n = 0;

    //private TextView[] questionTextView = new TextView[questionText.length];

    public Exchange(String[] answerText, StringBuffer questionText, String[] hintText, String[] answers, String[] gapText, int[] correctAnswers, MainActivity mainActivity) {
            this.questionText = questionText;
            this.answerText = answerText;
            this.hintText = hintText;
            this.answers = answers;
            this.gapText = gapText;
            this.correctAnswers = correctAnswers;
            this.mainActivity = mainActivity;
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

    public SpannableString checkHint(){
        StringBuffer stringBuffer = questionText;
        final Matcher matcher = Pattern.compile("#\\s*(\\w+)").matcher(stringBuffer);
        while (matcher.find()){
            final String word = matcher.group(1);
            int wordIndex = stringBuffer.indexOf(word) - 1;
            wordIndexList.add(wordIndex);
            wordList.add(word);
        }
        //removing hashtags(button markers)
        for (int i = 0; i < wordIndexList.size(); i++){
            //StringBuilder stringBuilder = new StringBuilder(stringBuffer);
            stringBuffer.deleteCharAt(wordIndexList.get(i) - i);

            //String resultString = stringBuilder.toString();
            //questionText = resultString;
        }
        /*
        for (int i = 0; i < wordIndexList.size(); i++)
        {
            for (int j = i; j < wordIndexList.size(); j++)
            {
                wordIndexList.set(j, wordIndexList.get(j) - 1);
            }
        }
        */
        SpannableString spannableString = new SpannableString(stringBuffer);
        for (int i = 0; i < wordIndexList.size(); i++){
            wordCount = i;
            ClickableSpan clickableSpan = new ClickableSpan() {//final
                @Override
                public void onClick(@NonNull View widget) {
                    //Simonas code example: https://stackoverflow.com/questions/15488238/using-android-getidentifier
                    int currentWord = 0;
                    //Getting text of clickable span
                    //-------------------------------------
                    TextView tv = (TextView) widget;
                    Spanned s = (Spanned) tv.getText();
                    int start = s.getSpanStart(this);
                    int end = s.getSpanEnd(this);
                    String clickableSpanString = s.subSequence(start, end).toString();
                    //-------------------------------------
                    //finding the correct image to show
                    //-------------------------------------
                    for(int j = 0; j < getWordCount() + 1; j++)
                    {
                        if(wordList.get(j).equalsIgnoreCase(clickableSpanString))
                        {
                            currentWord = j;
                            break;
                        }
                    }
                    //-------------------------------------

                    int resId = mainActivity.getResources().getIdentifier(getWordFile(currentWord), "drawable", mainActivity.getPackageName());
                    mainActivity.getHintImage().setImageResource(resId);        //Setting the image
                    //  mainActivity.getHintImage();
                    /* Commented out since it removes image after 10 secs regardless if any  new hints were shown since then
                    new CountDownTimer(10000, 1000){
                        public void onTick(long milliUntilFinished){
                            //addfunction
                        }
                        public void onFinish(){
                            mainActivity.getHintImage().setImageResource(0);
                        }
                    }.start();
                    */
                }
            };
            spannableString.setSpan(clickableSpan, wordIndexList.get(i) - i, wordIndexList.get(i) - i + wordList.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public void checkAnswer()
    {
        fileRead.getAllAnswers();
    }
    //-----------------
    //Simonas code
    public String getWordFile(int i)
    {
        return englishifize(wordList.get(i));
    }
    public int getWordCount()
    {
        return wordCount;
    }
    public String englishifize(String word)
    {
        word = word.replaceAll("æ", "ae");
        word = word.replaceAll("ø", "o");
        word = word.replaceAll("å", "aa");
        word = word.replaceAll("Æ", "Ae");
        word = word.replaceAll("Ø", "O");
        word = word.replaceAll("Å", "Aa");
        return word;
    }
    //-----------------


    /*void showHint(int x) {

    }*/
}