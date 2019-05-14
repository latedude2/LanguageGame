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
    private StringBuffer questionText; //array for normal text of other character
    private StringBuffer answerText; //array for already written text of answer
    private StringBuffer[] answers; //array of all 6 possible answers
    private TextView answerTextView;            //Text view to hold the text of the user
    private TextView dialogueTextView;          //Text view to hold the text of the NPC
    private TextView[] answerButtonsTextView = new TextView[6];

    MainActivity mainActivity;
    ConversationController parentConversationController;

    private ArrayList<Integer> wordIndexList = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();
    private int wordCount = 0;

    private int[] correctAnswers; //answers that are possible to be correct
    private String usersAnswerUnchanged;    //User's unedited answer to the NPC

    private ArrayList<Integer> ansWordIndexList = new ArrayList<>();
    private ArrayList<String> ansWordList = new ArrayList<>();
    int index = 0;

    private String fullAnswer;
    private int answerSlotCount = 0;
    private SelectedAnswer[] selectedAnswers = new SelectedAnswer[6];
    private int answerIndex;

    private MediaPlayer sentenceAudio;

    private FileRead fileRead; //creates the file object for all the Strings to be created there


    public Exchange(int index, MainActivity mainActivity, ConversationController conversationController) {
        this.mainActivity = mainActivity;
        this.parentConversationController = conversationController;

        this.dialogueTextView = mainActivity.findViewById(R.id.dialogue_text);
        this.answerTextView = mainActivity.findViewById(R.id.answer_text);

        for (int i = 0; i < answerButtonsTextView.length; i++) {
            String number = Integer.toString(i);
            String viewText = "answer_button_text_" + number;
            int textViewId = mainActivity.getResources().getIdentifier(viewText, "id", mainActivity.getPackageName());
            this.answerButtonsTextView[i] = mainActivity.findViewById(textViewId);
        }
        loadExchange(index);

    }
    public void loadExchange(int exchangeId)
    {
        String fileIndex = Integer.toString(exchangeId); //use if it complains about using integer in the String in the following line
        String IDToString = "exchange" + fileIndex; //creates a String name of the file to use in the following line
        int fileID = mainActivity.getResources().getIdentifier(IDToString,"raw", mainActivity.getPackageName());
        InputStream inputStream = (mainActivity.getResources().openRawResource(fileID));
        fileRead = new FileRead(inputStream);
        fileRead.read();
        questionText = fileRead.getQuestionText();
        answerText = fileRead.getAnswerText();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        dialogueTextView.setText(checkHint());
        dialogueTextView.setMovementMethod(LinkMovementMethod.getInstance());
        answerTextView.setText(checkGap());
        answers = fileRead.getAllAnswers();
        for (int j = 0; j < answerButtonsTextView.length; j++){
            answerButtonsTextView[j].setText(takeAnswers(j));
        }
        resetSelectedAnswers();
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
        sentenceAudio = MediaPlayer.create(mainActivity, idOfAudioFile);
        playAudio();
    }

    private void playAudio() {
        if (sentenceAudio.isPlaying()) {
            sentenceAudio.seekTo(0); //continues playing the audio from the beginning
        } else sentenceAudio.start();
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
                    parentConversationController.getHintImage().setVisibility(View.VISIBLE); //make hint view visible
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
        word = word.toLowerCase();
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
        if (checkAnswer())
        {
            parentConversationController.nextExchange();
        }
        else
        {
            resetAllWordInputFields();
            showAnswerText();
        }
    }
    void makeButtonDissapear(View view)
    {
        view.setVisibility(View.GONE);
        getAnswerButtonTextView(view).setVisibility(View.GONE);
    }
    void addAnswer(View view)
    {
        String answerTextToPut = getTextOfClickedAnswerButton(view);
        //makeButtonDissapear(view);

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
        answerTextView = getAnswerButtonTextView(view);
        return answerTextView.getText().toString();
    }
    TextView getAnswerButtonTextView(View view)
    //returns the text view of the clicked answer button
    {
        ImageView answer = (ImageView) view;
        int id = answer.getId();
        String answerTextName = answer.getResources().getResourceName(id);
        char takeNum = answerTextName.charAt(answerTextName.length()-1);
        answerIndex = Integer.parseInt(String.valueOf(takeNum));
        String textViewName = "answer_button_text_" + takeNum;
        int idOfTextView = mainActivity.getResources().getIdentifier(textViewName, "id", mainActivity.getPackageName());
        return mainActivity.findViewById(idOfTextView);
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
    public void resetAllWordInputFields()
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            selectedAnswers[i].word = "____";
        }
    }

    public boolean getCheckAnswer(){
        return checkAnswer();
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
    public void showAnswerText()
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