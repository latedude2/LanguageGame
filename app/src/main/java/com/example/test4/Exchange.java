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
    private StringBuffer answerText; //array for already written text of answer
    private String[] gapText; // array for gaps in the answer text
    private StringBuffer[] answers; //array of all 6 possible answers

    MainActivity mainActivity = new MainActivity();
    FileRead fileRead = new FileRead();

    private ArrayList<Integer> wordIndexList = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();
    private int wordCount = 0;

    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers; //answers that are possible to be correct

    private ArrayList<Integer> ansWordIndexList = new ArrayList<>();
    private ArrayList<String> ansWordList = new ArrayList<>();
    int index = 0;

    int n = 0;

    //private TextView[] questionTextView = new TextView[questionText.length];


    public Exchange(StringBuffer answerText, StringBuffer questionText, StringBuffer[] answers, int[] correctAnswers, MainActivity mainActivity) {


            this.questionText = questionText;
            this.answerText = answerText;
            this.answers = answers;
            this.correctAnswers = correctAnswers;
            this.mainActivity = mainActivity;
    }

    public SpannableString checkGap(){
        StringBuffer stringBuffer = answerText;
        final Matcher matcher = Pattern.compile("&\\s*(\\w+)").matcher(stringBuffer);
        while (matcher.find()){
            final String word = matcher.group(1);
            int wordIndex = stringBuffer.indexOf(word) - 1;
            ansWordIndexList.add(wordIndex);
            ansWordList.add(word);
        }
        correctAnswers = new int[ansWordIndexList.size()];

        String gap = "____";
        //removing & (button markers) and taking numbers of correct answers
        for (int i = 0; i < ansWordIndexList.size(); i++){
            stringBuffer.deleteCharAt(ansWordIndexList.get(i) - i + (gap.length() - 1) * i);
            char number = stringBuffer.charAt(ansWordIndexList.get(i) - i + (gap.length() - 1) * i);
            correctAnswers[index] = java.lang.Character.getNumericValue(number);
            stringBuffer.deleteCharAt(ansWordIndexList.get(i) - i + (gap.length() - 1) * i);
            stringBuffer.insert(ansWordIndexList.get(i) - i + (gap.length() - 1) * i, gap);
            index++;
        }

        SpannableString spannableString = new SpannableString(stringBuffer);
        index = 0;
        return spannableString;
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
            stringBuffer.deleteCharAt(wordIndexList.get(i) - i);
        }
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
                    int resId = mainActivity.getResources().getIdentifier(getWordFile(currentWord), "drawable", mainActivity.getPackageName());
                    mainActivity.getHintImage().setImageResource(resId);
                }
            };


            spannableString.setSpan(clickableSpan, wordIndexList.get(i) - i, wordIndexList.get(i) - i + wordList.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

   public SpannableString takeAnswers(int answerIndex)
    {
        StringBuffer stringBuffer = answers[answerIndex];
        SpannableString spannableString = new SpannableString(stringBuffer);
        return spannableString;
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
}