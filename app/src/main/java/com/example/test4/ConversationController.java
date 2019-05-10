package com.example.test4;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class ConversationController extends Instance{


    //private ImageView backButton;

    private OurButton continueButton;

    private int sizeSix = 6;
    private int numExchanges;
    private int currentExchangeID;
    private int exchangeCounter = 0;
    private int numOfConversations = 5;

    private int conversationId;
    private int exchangeId;

    private Exchange exchange;
    private Exchange[] exchanges = new Exchange[numExchanges];
    private GameObject background;
    private FileRead fileRead;

    private Exchange[] currentConversation = new Exchange[numOfConversations];

    //All conversation Elements
    private ImageView npcDialogueView;      //Image of the character you are having the conversation with
    private ImageView hintImage;            //Image view to show the hint of a word
    private TextView dialogueText;          //Text view to hold the text of the NPC
    private TextView answerText;            //Text view to hold the text of the user
    private ImageView conversationBackground;     //Image view to show the background of a conversation
    private ImageView answerTextField;      //Image where the answer is displayed on
    private ImageView speakerButton;        //Clickable image to play the sound
    private ImageView submitButton;        //Clickable image to submit the answer
    private ImageView exitButton;           //Clickable image to exit the conversation
    private TextView[] answerButtonsTextView = new TextView[sizeSix]; //List of the answer choices displayed
    private ImageView[] answerButtons = new ImageView[sizeSix];   //List of Images where the answer choices are displayed on

    //All world view Elements
    private ImageView dPadBackground;       //Image of the area where the DPad is displayed
    private ImageView dPadImageView;        //To be changed later will be divided by 4
    private Button upButton;
    private Button downButton;
    private Button rightButton;
    private Button leftButton;

    ////////////////////////////////////////////////////

    ConversationController(MainActivity mainActivity){
        this.npcDialogueView = mainActivity.findViewById(R.id.npc_dialogue_view);
        this.hintImage = mainActivity.findViewById(R.id.hint_img);
        this.dialogueText = mainActivity.findViewById(R.id.dialogue_text);
        this.answerText = mainActivity.findViewById(R.id.answer_text);
        this.conversationBackground = mainActivity.findViewById(R.id.conversation_background);
        this.answerTextField = mainActivity.findViewById(R.id.answer_text_field);
        this.speakerButton = mainActivity.findViewById(R.id.speaker_button);
        this.submitButton = mainActivity.findViewById(R.id.submit_button);
        this.exitButton = mainActivity.findViewById(R.id.exit_button);
        for (int j = 0; j < sizeSix; j++) {
            String number = Integer.toString(j);
            String viewText = "answer_button_text_" + number;
            String imageView = "answer_button_" + number;
            int textViewId = mainActivity.getResources().getIdentifier(viewText, "id", mainActivity.getPackageName());
            int imageViewId= mainActivity.getResources().getIdentifier(imageView, "id", mainActivity.getPackageName());
            this.answerButtonsTextView[j] = mainActivity.findViewById(textViewId);
            this.answerButtons[j]=mainActivity.findViewById(imageViewId);
        }


        //Dpad elements to be changed later
        upButton = mainActivity.findViewById(R.id.up_button);
        downButton = mainActivity.findViewById(R.id.down_button);
        leftButton = mainActivity.findViewById(R.id.left_button);
        rightButton = mainActivity.findViewById(R.id.right_button);
        dPadBackground = mainActivity.findViewById(R.id.d_pad_background);
        dPadImageView = mainActivity.findViewById(R.id.d_pad_imageview);

        ///////////////////////////////////////////////////////
    }

    ConversationController(int numExchanges, int conversationId, int exchangeId, Exchange exchange, MainActivity mainActivity){
        this.numExchanges = numExchanges;
        this.conversationId = conversationId;
        this.exchangeId = exchangeId;
        this.exchange = exchange;
    }



    public void makeConversation(){
        for (int i = 0; i < numExchanges; i++) {
            currentConversation[conversationId] = exchanges[i + numExchanges + exchangeId];
        }
    }

    public Exchange[] getExchanges() {
        for (int i = 0; i < numExchanges; i++) {
            exchanges[i] = getExchange();
        }
        return exchanges;
    }

    public void setExchanges(Exchange[] exchanges) {
        this.exchanges = exchanges;
    }

    public void hideConversationElements(){
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            getAnswerButtons()[j].setVisibility(View.GONE);
            getAnswerButtonsTextView()[j].setVisibility(View.GONE);
        }
        getNpcDialogueView().setVisibility(View.GONE);
        getHintImage().setVisibility(View.GONE);
        getDialogueText().setVisibility(View.GONE);
        getAnswerText().setVisibility(View.GONE);
        getConversationBackground().setVisibility(View.GONE);
        getAnswerTextField().setVisibility(View.GONE);
        getSpeakerButton().setVisibility(View.GONE);
        getSubmitButton().setVisibility(View.GONE);
        getExitButton().setVisibility(View.GONE);
        //Showing DPad, to be moved later

    }

    public void showConversationElements(){
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            getAnswerButtons()[j].setVisibility(View.VISIBLE);
            getAnswerButtonsTextView()[j].setVisibility(View.VISIBLE);
        }
        getNpcDialogueView().setVisibility(View.VISIBLE);
        getHintImage().setVisibility(View.VISIBLE);
        getDialogueText().setVisibility(View.VISIBLE);
        getAnswerText().setVisibility(View.VISIBLE);
        getConversationBackground().setVisibility(View.VISIBLE);
        getAnswerTextField().setVisibility(View.VISIBLE);
        getSpeakerButton().setVisibility(View.VISIBLE);
        getSubmitButton().setVisibility(View.VISIBLE);
        getExitButton().setVisibility(View.VISIBLE);
        //Hiding DPad, to be moved later
    }


    public ImageView getNpcDialogueView() {
        return npcDialogueView;
    }

    public ImageView getHintImage() {
        return hintImage;
    }

    public TextView getDialogueText() {
        return dialogueText;
    }

    public TextView getAnswerText() {
        return answerText;
    }

    public ImageView getConversationBackground() {
        return conversationBackground;
    }

    public ImageView getAnswerTextField() {
        return answerTextField;
    }

    public ImageView getSpeakerButton() {
        return speakerButton;
    }

    public ImageView getSubmitButton() {
        return submitButton;
    }

    public ImageView getExitButton() {
        return exitButton;
    }

    public TextView[] getAnswerButtonsTextView() {
        return answerButtonsTextView;
    }

    public ImageView[] getAnswerButtons() {
        return answerButtons;
    }

    public int getExchangeCounter() {
        return exchangeCounter;
    }

    public void setExchangeCounter(int exchangeCounter) {
        this.exchangeCounter = exchangeCounter;
    }

    public int getCurrentExchangeID() {
        return currentExchangeID;
    }

    public void setCurrentExchangeID(int currentExchangeID) {
        this.currentExchangeID = currentExchangeID;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public FileRead getFileRead() {
        return fileRead;
    }

    public void setFileRead(FileRead fileRead) {
        this.fileRead = fileRead;
    }

}
