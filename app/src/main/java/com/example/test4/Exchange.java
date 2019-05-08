package com.example.test4;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Exchange extends Instance {
    private GameObject[] UIObjects; //images shown after pressing the hintButton

    private StringBuffer questionText; //array for normal text of other character
    private StringBuffer answerText; //array for already written text of answer
    private StringBuffer[] answers; //array of all 6 possible answers

    MainActivity mainActivity = new MainActivity();

    private ArrayList<Integer> wordIndexList = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();
    private int wordCount = 0;

    private int[] selectAnswers = new int[6]; //all of the possible answers
    private int[] correctAnswers; //answers that are possible to be correct
    private String usersAnswerUnchanged;    //User's unedited answer to the NPC

    private ArrayList<Integer> ansWordIndexList = new ArrayList<>();
    private ArrayList<String> ansWordList = new ArrayList<>();
    int index = 0;

    private String fullAnswer;
    private int answerSlotCount = 0;
    private SelectedAnswer[] selectedAnswers = new SelectedAnswer[6];
    private int answerIndex;


    public Exchange(StringBuffer answerText, StringBuffer questionText, StringBuffer[] answers, MainActivity mainActivity) {
            this.questionText = questionText;
            this.answerText = answerText;
            this.answers = answers;
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
        usersAnswerUnchanged = spannableString.toString();
        return spannableString;
    }


    //plays all sentence of question
    public void sentencePlay(ImageView speaker_button, int idOfAudioFile) {
        final MediaPlayer sentenceAudio = MediaPlayer.create(mainActivity, idOfAudioFile);
        speaker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sentenceAudio.isPlaying()) {
                    sentenceAudio.seekTo(0); //continues playing the audio from the beginning
                } else sentenceAudio.start();
            }
        });
    }

    public SpannableString checkHint()
    {
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

                    //-------------------------------------
                    //audio to play
                    //-------------------------------------
                    int idOfAudioFile = mainActivity.getResources().getIdentifier(getWordFile(currentWord), "raw", mainActivity.getPackageName());
                    final MediaPlayer audio = MediaPlayer.create(mainActivity, idOfAudioFile);
                            if (audio.isPlaying()) {
                                audio.seekTo(0);
                            } else audio.start();
                }
            };
            spannableString.setSpan(clickableSpan, wordIndexList.get(i) - i, wordIndexList.get(i) - i + wordList.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

   public SpannableString takeAnswers(int answerIndex) {
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
    public String getUsersAnswerUnchanged()
    {
        return usersAnswerUnchanged;
    }
    public int[] getCorrectAnswers() {
        return correctAnswers;
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
   void resetSelectedAnswers()
   {
       for(int i = 0; i < 6; i++)
       {
           selectedAnswers[i] = new SelectedAnswer("____");
       }
   }
    void submitAnswer(View view)
    {
        TextView answerField = mainActivity.findViewById(R.id.answer_text);
        if (checkAnswer())
        {
            answerField.setText("You're a good boy");
        }
        else
        {
            resetAllWordInputFields();
            showAnswerText();
        }
    }

    void addAnswer(View view)
    {
        String answerTextToPut = getTextOfClickedAnswerButton(view);

        TextView answerField = mainActivity.findViewById(R.id.answer_text);                  //Get answer text Text View
        answerField.setMovementMethod(LinkMovementMethod.getInstance());        //Make the text view clickable. This enables us to add ClickableSpan to this Text View

        prepareStringForAddingWords();


        if(answerField.getText().toString().contains("____"))   //If there is a slot to put the new word in
        {
            putWordInSlot(answerTextToPut, answerIndex);                     //Put the word in the first available slot
            SpannableString spanString = buildSpannableString(selectedAnswers, fullAnswer, answerSlotCount);        //Build the string by adding the clickable parts to it
            answerField.setText(spanString);
        }
    }
    private void prepareStringForAddingWords()
    //reseting the full answer to represent the original text, giving each slot its own number, counting the amount of slots
    {
        fullAnswer = getUsersAnswerUnchanged();        //reseting the full answer to represent the original text
        answerSlotCount = 0;
        String tempString = fullAnswer;
        while((tempString).contains("____"))
        {
            tempString = tempString.substring(tempString.indexOf("____") + 4);          //Taking the part of the string that does not have the already found "____"
            fullAnswer = fullAnswer.replaceFirst("____", "#" + answerSlotCount);        //giving each slot its own number
            answerSlotCount++;                                  //counting the amount of slots
        }
    }

    private String getTextOfClickedAnswerButton(View view)
    //Get the text of the clicked answer image view. This connects the image view to the text view in a way.
    {
        ImageView answer = (ImageView) view;
        int id = answer.getId();
        String answerTextName = answer.getResources().getResourceName(id);
        char takeNum = answerTextName.charAt(answerTextName.length()-1);
        answerIndex = Integer.parseInt(String.valueOf(takeNum));
        String textViewName = "answer_button_text_" + takeNum;
        int idOfTextView = mainActivity.getResources().getIdentifier(textViewName, "id", mainActivity.getPackageName());
        TextView answerTextView = mainActivity.findViewById(idOfTextView);
        return answerTextView.getText().toString();
    }
    private void putWordInSlot(String ans, int index)
    //Sets and empty selected answer slot to a specified string
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            if(selectedAnswers[i].word == "____") {
                selectedAnswers[i].word = ans;
                selectedAnswers[i].IDForCheckingAnswer = index;
                break;
            }
        }
    }
    private void resetWordInputField(String answerToReset)
    //Remove the selected word from the list of selected words
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            if(selectedAnswers[i].word.equals(answerToReset))
            {
                selectedAnswers[i].word = "____";
            }
        }
    }
    private void resetAllWordInputFields()
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            selectedAnswers[i].word = "____";
        }
    }
    private boolean checkAnswer()
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            if(getCorrectAnswers()[i] != selectedAnswers[i].IDForCheckingAnswer)
            {
                return false;
            }
        }
        return true;
    }
    private void showAnswerText()
    //Update and show the answer text
    {
        prepareStringForAddingWords();
        SpannableString spanString = buildSpannableString(selectedAnswers, fullAnswer, answerSlotCount);
        TextView answerField = mainActivity.findViewById(R.id.answer_text);
        answerField.setText(spanString);
    }

    private SpannableString buildSpannableString(SelectedAnswer[] selectedAnswers, String stringToAddTo, int answerCount)
    {
        //Placing words in string
        for(int i = 0; i < answerCount; i++)
        {
            selectedAnswers[i].answerPositionIndex = stringToAddTo.indexOf("#" + i);        //We need to remember where the string is positioned to be able to set the clickable span
            stringToAddTo = stringToAddTo.replace("#" + i, selectedAnswers[i].word);
        }
        SpannableString spannableString = new SpannableString(stringToAddTo);
        for(int i = 0; i < answerCount; i++)
        {
            if(selectedAnswers[i].word != "____")
            {
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        //Getting text of clickable span
                        //-------------------------------------
                        TextView tv = (TextView) widget;
                        Spanned s = (Spanned) tv.getText();
                        int start = s.getSpanStart(this);
                        int end = s.getSpanEnd(this);
                        String clickableSpanString = s.subSequence(start, end).toString();

                        resetWordInputField(clickableSpanString);
                        showAnswerText();
                        makeButtonActiveAgain();
                    }
                };
                spannableString.setSpan(clickableSpan, selectedAnswers[i].answerPositionIndex, selectedAnswers[i].answerPositionIndex + selectedAnswers[i].word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }
    private void makeButtonActiveAgain()
    {

    }
}