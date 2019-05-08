package com.example.test4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends Activity {

    private ImageView backgroundMap;        //Image view to show the map
    private ImageView conversationBack;     //Image view to show the background of a conversation
    private DPad dPad;                      //Controls for walking
    private TextView answerText;            //Text view to hold the text of the user
    private TextView dialoguetext;          //Text view to hold the text of the NPC
    private ImageView hintImage;            //Image view to show the hint of a word
    private Exchange exchange;
    private TextView[] answerButtonsTextView = new TextView[6];


    private String fullAnswer;
    private int answerSlotCount = 0;
    private SelectedAnswer[] selectedAnswers = new SelectedAnswer[6];
    //private String[] selectedAnswers = new String[6];
    //private int[] answerPositionIndexes = new int[6];

    int i = 2; //index which counts which exchange it is currently

    int idOfImage;

    int[] answerNum;
    int timesAnswerChosen = 0;

    char[][] mapTiles; //2D array for the map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        loadLayoutImage();
        dPad.hideButtons();
        loadExchange();
        loadConverstationCharacterImage();

        loadMapStructure();
    }

    public void loadLayoutImage(){

        backgroundMap = findViewById(R.id.world_view);
        dPad = new DPad(backgroundMap, this);

        conversationBack = findViewById(R.id.conversation_view);

        idOfImage = getResources().getIdentifier("map", "drawable", getPackageName());
        backgroundMap.setImageResource(idOfImage);

        idOfImage = getResources().getIdentifier("background_convo", "drawable", getPackageName());
        conversationBack.setImageResource(idOfImage);

        ImageView submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        ImageView speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);

    }
    public void loadExchange()
    {
        String index = Integer.toString(i); //use if it complains about using integer in the String in the following line
        String id = "exchange" + index; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(id,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        FileRead file = new FileRead(inputStream); //creates the file object for all the Strings to be created there
        file.read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        exchange = new Exchange(file.getAnswerText(), file.getQuestionText(), file.getAllAnswers(), file.getCorrectAnswers(), this);
        dialoguetext = findViewById(R.id.dialogue_text);
        dialoguetext.setText(exchange.checkHint());
        dialoguetext.setMovementMethod(LinkMovementMethod.getInstance());

        answerText = findViewById(R.id.answer_text);
        answerText.setText(exchange.checkGap());

        for (i = 0; i < answerButtonsTextView.length; i++){
            String number = Integer.toString(i);
            String viewText = "answer_button_text_" + number;
            int textViewId = getResources().getIdentifier(viewText, "id", getPackageName());
            answerButtonsTextView[i] = findViewById(textViewId);
            answerButtonsTextView[i].setText(exchange.takeAnswers(i));
        }

        //Reseting selected answers
        for(int i = 0; i < 6; i++)
        {
            selectedAnswers[i] = new SelectedAnswer("____");
        }

    }

    public void loadConverstationCharacterImage(){
        ImageView imageView = findViewById(R.id.npc_dialogue_view);
        imageView.setImageResource(R.drawable.big_baker);
    }

    public void move_characterUp (View v){
        dPad.moveUp();
    }

    public void move_characterDown (View v){
        dPad.moveDown();
    }

    public void move_characterLeft (View v){
        dPad.moveLeft();
    }

    public void move_characterRight (View v){
        dPad.moveRight();
    }

    public ImageView getHintImage(){
        hintImage = findViewById(R.id.hint_img);
        return hintImage;
    }
    public void answerClick(View view){
        ImageView answer = (ImageView) view;
        int id = answer.getId();
        String answerTextName = answer.getResources().getResourceName(id);
        char takeNum = answerTextName.charAt(answerTextName.length()-1);
        String textViewName = "answer_button_text_" + takeNum;
        int idOfTextView = getResources().getIdentifier(textViewName, "id", getPackageName());
        TextView answerTextView = findViewById(idOfTextView);
        String answerTextToPut = answerTextView.getText().toString();
        TextView answerField = findViewById(R.id.answer_text);
        answerField.setMovementMethod(LinkMovementMethod.getInstance());        //Make the text view clickable. This enables us to add ClickableSpan to this Text View



        fullAnswer = exchange.getUsersAnswerUnchanged();
        answerSlotCount = 0;
        String tempString = fullAnswer;
        while((tempString).contains("____"))
        {
            tempString = tempString.substring(tempString.indexOf("____") + 4);
            fullAnswer = fullAnswer.replaceFirst("____", "#" + answerSlotCount);
            answerSlotCount++;
        }

        if(answerField.getText().toString().contains("____"))   //If there is a slot to put the new word in
        {
            putWordInSlot(answerTextToPut);
            SpannableString spanString = buildSpannableString(selectedAnswers, fullAnswer, answerSlotCount);
            answerField.setText(spanString);
        }
    }
    void putWordInSlot(String ans)
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            if(selectedAnswers[i].word == "____") {
                selectedAnswers[i].word = ans;
                break;
            }
        }
    }
    public void onSubmitClick(View view){
        int[] correctAnswers = exchange.getCorrectAnswers();
        TextView answerField = findViewById(R.id.answer_text);
        if (answerNum == correctAnswers)
        {
            answerField.setText("You're a good boy");
        }
        else
        {
            answerField.setText("You're a bad boy");
        }
    }

    private void resetWordInputField(String answerToReset)
    {
        for(int i = 0; i < answerSlotCount; i++)
        {
            if(selectedAnswers[i].word.equals(answerToReset))
            {
                selectedAnswers[i].word = "____";
            }
        }
    }
    private void showAnswerText()
    {
        fullAnswer = exchange.getUsersAnswerUnchanged();
        answerSlotCount = 0;
        String tempString = fullAnswer;
        while((tempString).contains("____"))
        {
            tempString = tempString.substring(tempString.indexOf("____") + 4);
            fullAnswer = fullAnswer.replaceFirst("____", "#" + answerSlotCount);
            answerSlotCount++;
        }
        SpannableString spanString = buildSpannableString(selectedAnswers, fullAnswer, answerSlotCount);
        TextView answerField = findViewById(R.id.answer_text);
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

    public void loadMapStructure(){
        String idName = "map_structure"; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(idName,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        FileRead fileStructure = new FileRead(inputStream); //creates the file object for all the Strings to be created there
        char[][] structure = fileStructure.readStructureChars();

    }

}
