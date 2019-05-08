package com.example.test4;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends Activity {

    private ImageView backgroundMap;        //Image view to show the map

    private DPad dPad;                      //Controls for walking




    //All conversation elements
    private ImageView npcDialogueView;      //Image of the character you are having the conversation with
    private ImageView hintImage;            //Image view to show the hint of a word
    private TextView dialogueText;          //Text view to hold the text of the NPC
    private TextView answerText;            //Text view to hold the text of the user
    private ImageView conversationLayout;     //Image view to show the background of a conversation
    private ImageView answerTextField;      //Image where the answer is displayed on
    private ImageView speakerButton;        //Clickable image to play the sound
    private ImageView submitButton;        //Clickable image to submit the answer
    private TextView[] answerButtonsTextView = new TextView[6]; //List of the answer choices displayed
    private ImageView[] answerButtons = new ImageView[6];   //List of Images where the answer choices are displayed on

    //All world view elements

    private Exchange exchange;

    private  ConversationController conversationController;

    //private TextView[] answerButtonsTextView = new TextView[6];
    private ImageView speaker_button;
    private ImageView submit_button;


    private FileRead file;


    int exchangeIndex = 6; //index which counts which exchange it is currently

    int[] answerNum;

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

    public void beGone(View view){
        conversationController = new ConversationController(this);
        conversationController.exitConversation();
    }

    public void beCome(){

    }

    public void loadLayoutImage(){

        backgroundMap = findViewById(R.id.world_view);
        dPad = new DPad(backgroundMap, this);

        conversationLayout = findViewById(R.id.conversation_layout);

        int idOfMap = getResources().getIdentifier("map", "drawable", getPackageName());
        backgroundMap.setImageResource(idOfMap);

        int idOfImage = getResources().getIdentifier("background_convo", "drawable", getPackageName());
        conversationLayout.setImageResource(idOfImage);

        submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);
    }

    //loads all the elements of the exchange view
    public void loadExchange() {
        String index = Integer.toString(exchangeIndex); //use if it complains about using integer in the String in the following line
        String id = "exchange" + index; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(id, "raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        file = new FileRead(inputStream); //creates the file object for all the Strings to be created there
        file.read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange

        exchange = new Exchange(file.getAnswerText(), file.getQuestionText(), file.getAllAnswers(), this);
        dialogueText = findViewById(R.id.dialogue_text);
        dialogueText.setText(exchange.checkHint());
        dialogueText.setMovementMethod(LinkMovementMethod.getInstance());

        answerText = findViewById(R.id.answer_text);
        answerText.setText(exchange.checkGap());

        //exchange.takeAnswers(this.i);
        for (int j = 0; j < answerButtonsTextView.length; j++) {

            for (int i = 0; i < answerButtonsTextView.length; i++) {
                String number = Integer.toString(i);
                String viewText = "answer_button_text_" + number;
                int textViewId = getResources().getIdentifier(viewText, "id", getPackageName());
                answerButtonsTextView[i] = findViewById(textViewId);
                answerButtonsTextView[i].setText(exchange.takeAnswers(i));
            }

            onSoundViewClick();
            exchange.resetSelectedAnswers();

        }

    }
        public void loadConverstationCharacterImage () {
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

        public ImageView getNpcDialogueView () {
            npcDialogueView = findViewById(R.id.npc_dialogue_view);
            return npcDialogueView;
        }

        public ImageView getHintImage () {
            hintImage = findViewById(R.id.hint_img);
            return hintImage;
        }

        public TextView getDialogueText () {
            dialogueText = findViewById(R.id.dialogue_text);
            return dialogueText;
        }

        public TextView getAnswerText () {
            answerText = findViewById(R.id.answer_text);
            return answerText;
        }

        public ImageView getConversationLayout () {
            conversationLayout = findViewById(R.id.conversation_layout);
            return conversationLayout;
        }

        public ImageView getAnswerTextField () {
            answerTextField = findViewById(R.id.answer_text_field);
            return answerTextField;
        }

        public ImageView getSpeakerButton () {
            speakerButton = findViewById(R.id.speaker_button);
            return speakerButton;
        }

        public ImageView getSubmitButton () {
            submitButton = findViewById(R.id.submit_button);
            return submitButton;
        }

        public TextView[] getAnswerButtonsTextView () {
            for (int j = 0; j < answerButtonsTextView.length; j++) {
                String number = Integer.toString(j);
                String viewText = "answer_button_text_" + number;
                int textViewId = getResources().getIdentifier(viewText, "id", getPackageName());
                answerButtonsTextView[j] = findViewById(textViewId);
            }
            return answerButtonsTextView;
        }

        public ImageView[] getAnswerButtons () {
            for (int j = 0; j < answerButtons.length; j++) {
                String number = Integer.toString(j);
                String imageView = "answer_button_" + number;
                int imageViewId = getResources().getIdentifier(imageView, "id", getPackageName());
                answerButtons[j] = findViewById(imageViewId);
            }
            return answerButtons;
        }

        public void answerClick (View view){
            ImageView answer = (ImageView) view;
            int id = answer.getId();
            String answerTextName = answer.getResources().getResourceName(id);
            char takeNum = answerTextName.charAt(answerTextName.length() - 1);
            String textViewName = "answer_button_text_" + takeNum;
            int idOfTextView = getResources().getIdentifier(textViewName, "id", getPackageName());
            TextView answerTextView = findViewById(idOfTextView);
            String answerTextToPut = answerTextView.getText().toString();
            TextView answerField = findViewById(R.id.answer_text);
            answerField.setMovementMethod(LinkMovementMethod.getInstance());        //Make the text view clickable. This enables us to add ClickableSpan to this Text View


            fullAnswer = exchange.getUsersAnswerUnchanged();
            answerSlotCount = 0;
            String tempString = fullAnswer;
            while ((tempString).contains("____")) {
                tempString = tempString.substring(tempString.indexOf("____") + 4);
                fullAnswer = fullAnswer.replaceFirst("____", "#" + answerSlotCount);
                answerSlotCount++;
            }
            //System.out.println(fullAnswer);

            if (answerField.getText().toString().contains("____"))   //If there is a slot to put the new word in
            {
                putWordInSlot(answerTextToPut);
                SpannableString spanString = buildSpannableString(selectedAnswers, fullAnswer, answerSlotCount);
                answerField.setText(spanString);
            }
        }
        //void putWordInSlot(String ans)

        public void answerClick (View view)
        {
            exchange.addAnswer(view);

        }


        public void onSubmitClick (View view)
        {
            exchange.submitAnswer(view);


        }

        public void onSoundViewClick () {
            String audioFileName = "sentence" + exchangeIndex;
            final int idOfAudioFile = getResources().getIdentifier(audioFileName, "raw", getPackageName());
            exchange.sentencePlay(speaker_button, idOfAudioFile);
        }


        //creates the 2D array mapTiles, which holds the structure of the map
        public void loadMapStructure () {
            String idName = "map_structure"; //creates a String name of the file to use in the following line
            int idOfFile = getResources().getIdentifier(idName, "raw", getPackageName());
            InputStream inputStream = this.getResources().openRawResource(idOfFile);
            FileRead fileStructure = new FileRead(inputStream); //creates the file object for all the Strings to be created there
            mapTiles = fileStructure.readStructureChars(); //reads all of the chars in the structure and adds them to the 2D array
        }
    }
}
