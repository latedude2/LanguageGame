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
    private int currentExchangeID;
    private int exchangeCounter = 0;
    private String name;


    private Exchange exchange;
    private int[] exchanges;
    private MainActivity mainActivity;

    //All conversation Elements
    private ImageView npcDialogueView;      //Image of the character you are having the conversation with
    private ImageView hintImage;            //Image view to show the hint of a word
    private TextView dialogueText;          //Text view to hold the text of the NPC
    private TextView answerText;            //Text view to hold the text of the user
    private ImageView conversationBlur;     //Image view to blur out world during conversations
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

    ////////////////////////////////////////////////////

    ConversationController(MainActivity mainActivity){


        //Dpad elements to be changed later
        dPadBackground = mainActivity.findViewById(R.id.d_pad_background);
        dPadImageView = mainActivity.findViewById(R.id.d_pad_imageview);

        ///////////////////////////////////////////////////////
    }

    ConversationController(int[] exchanges, MainActivity mainActivity, String name){
        this.name = name;
        this.npcDialogueView = mainActivity.findViewById(R.id.npc_dialogue_view);
        this.hintImage = mainActivity.findViewById(R.id.hint_img);
        this.dialogueText = mainActivity.findViewById(R.id.dialogue_text);
        this.answerText = mainActivity.findViewById(R.id.answer_text);
        this.conversationBlur = mainActivity.findViewById(R.id.conversation_blur);
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
            this.answerButtons[j] = mainActivity.findViewById(imageViewId);
        }

        this.exchanges = exchanges;
        this.mainActivity = mainActivity;
    }

    public Exchange getCurrentExchange()
    {
        return exchange;
    }

    public void startConversation()
    {
        currentExchangeID = 0;
        showConversationElements();
        exchange = new Exchange(exchanges[0], mainActivity, this);

        if(name.equals("Niels"))
        {
            npcDialogueView.setImageResource(R.drawable.big_niels);
        }
        else if (name.equals("Baker"))
        {
            npcDialogueView.setImageResource(R.drawable.big_baker);
        }
        else if(name.equals("Clerk"))
        {
            npcDialogueView.setImageResource(R.drawable.big_clerk);
        }
        else if(name.equals("Old"))
        {
            npcDialogueView.setImageResource(R.drawable.big_old);
        }

    }
    public void nextExchange()
    {
        hintImage.setVisibility(View.GONE);
        currentExchangeID++;
        if(exchanges.length > currentExchangeID)
        {
            exchange = new Exchange(exchanges[currentExchangeID], mainActivity, this);
        }
        else
        {
            if(name.equals("Niels"))
            {
                mainActivity.talkedToNiels = true;
            }
            else if (name.equals("Old"))
            {
                mainActivity.talkedToOld = true;
            }
            else if (name.equals("Baker"))
            {
                mainActivity.gotBread = true;
            }
            else if(name.equals("Clerk"))
            {
                mainActivity.gotMilk = true;
            }

            hideConversationElements();
            mainActivity.getdPad().showDPad();
        }
    }
    public void hideConversationElements(){
        /*
        this.npcDialogueView = mainActivity.findViewById(R.id.npc_dialogue_view);
        this.hintImage = mainActivity.findViewById(R.id.hint_img);
        this.dialogueText = mainActivity.findViewById(R.id.dialogue_text);
        this.answerText = mainActivity.findViewById(R.id.answer_text);
        this.conversationBlur = mainActivity.findViewById(R.id.conversation_blur);
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
            this.answerButtons[j] = mainActivity.findViewById(imageViewId);
        }
        */
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            answerButtons[j].setVisibility(View.GONE);
            answerButtonsTextView[j].setVisibility(View.GONE);
        }
        getNpcDialogueView().setVisibility(View.GONE);
        getHintImage().setVisibility(View.GONE);
        getDialogueText().setVisibility(View.GONE);
        getAnswerText().setVisibility(View.GONE);
        getConversationBlur().setVisibility(View.GONE);
        getConversationBackground().setVisibility(View.GONE);
        getAnswerTextField().setVisibility(View.GONE);
        getSpeakerButton().setVisibility(View.GONE);
        getSubmitButton().setVisibility(View.GONE);
        getExitButton().setVisibility(View.GONE);
        //Showing DPad, to be moved later
    }

    public void showConversationElements(){
        this.npcDialogueView = mainActivity.findViewById(R.id.npc_dialogue_view);
        this.hintImage = mainActivity.findViewById(R.id.hint_img);
        this.dialogueText = mainActivity.findViewById(R.id.dialogue_text);
        this.answerText = mainActivity.findViewById(R.id.answer_text);
        this.conversationBlur = mainActivity.findViewById(R.id.conversation_blur);
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
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            answerButtons[j].setVisibility(View.VISIBLE);
            answerButtonsTextView[j].setVisibility(View.VISIBLE);
        }
        npcDialogueView.setVisibility(View.VISIBLE);
        hintImage.setVisibility(View.VISIBLE);
        dialogueText.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.VISIBLE);
        conversationBlur.setVisibility(View.VISIBLE);
        conversationBackground.setVisibility(View.VISIBLE);
        answerTextField.setVisibility(View.VISIBLE);
        speakerButton.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        exitButton.setVisibility(View.VISIBLE);
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

    public ImageView getConversationBlur(){
        return conversationBlur;
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

}
