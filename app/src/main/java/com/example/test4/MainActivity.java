package com.example.test4;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
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
    private ImageView exiteButton;

    private TextView[] answerButtonsTextView = new TextView[6];

    Exchange exchange;

    int i = 3; //index which counts which exchange it is currently

    int idOfImage;

    int[] answerNum;
    int timesAnswerChosen = 0;

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
    }



    public void loadLayoutImage(){

        backgroundMap = findViewById(R.id.world_view);
        dPad = new DPad(backgroundMap, this);

        conversationBack = findViewById(R.id.conversation_view);

        idOfImage = getResources().getIdentifier("map", "drawable", getPackageName());
        backgroundMap.setImageResource(idOfImage);

        idOfImage = getResources().getIdentifier("background", "drawable", getPackageName());
        conversationBack.setImageResource(idOfImage);

        ImageView submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        ImageView speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);

    }
    public void loadExchange(){

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

        //exchange.takeAnswers(this.i);
        for (i = 0; i < answerButtonsTextView.length; i++){
            String number = Integer.toString(i);
            String viewText = "answer_button_text_" + number;
            int textViewId = getResources().getIdentifier(viewText, "id", getPackageName());
            answerButtonsTextView[i] = findViewById(textViewId);
            answerButtonsTextView[i].setText(exchange.takeAnswers(i));
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


    public ImageView getExiteButton() {
        exiteButton = findViewById(R.id.exit_menu_button);
        return exiteButton;
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
        String fullAnswer = answerField.getText().toString();
        answerField.setText(fullAnswer.replaceFirst("____", answerTextToPut));

        answerNum[timesAnswerChosen] = java.lang.Character.getNumericValue(takeNum);
        timesAnswerChosen++;
    }

    public void onSubmitClick(View view){
        int[] correctAnswers = exchange.getCorrectAnswers();
        TextView answerField = findViewById(R.id.answer_text);
        if (answerNum == correctAnswers){

            answerField.setText("You're a good boy");
        } else {
            answerField.setText("You're a bad boy");
        }
    }
}
