package com.example.test4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ConversationController {

    private MainActivity mainActivity;
    private int answerButtonCount = 6;
    private String name;

    private int exchangeCounter = 0;
    private Exchange exchange;      //Current exchange happening in the conversation
    private int[] exchangeIndexes;        //Exchange indexes

    //All conversation view Elements
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
    private TextView[] answerButtonsTextView = new TextView[answerButtonCount]; //List of the answer choices displayed
    private ImageView[] answerButtons = new ImageView[answerButtonCount];   //List of Images where the answer choices are displayed on
    private ImageView dialogueScrollIndicator;    //Indicator to show that the dialogue continues below
    private ImageView answerScrollIndicator;    //Indicator to show that the dialogue continues below

    ConversationController(int[] exchangeIndexes, MainActivity mainActivity, String name){
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
        this.dialogueScrollIndicator = mainActivity.findViewById(R.id.dialogue_scrollIndicator);
        this.answerScrollIndicator = mainActivity.findViewById(R.id.answer_scrollIndicator);

        for (int j = 0; j < answerButtonCount; j++) {
            String number = Integer.toString(j);
            String viewText = "answer_button_text_" + number;
            String imageView = "answer_button_" + number;
            int textViewId = mainActivity.getResources().getIdentifier(viewText, "id", mainActivity.getPackageName());
            int imageViewId= mainActivity.getResources().getIdentifier(imageView, "id", mainActivity.getPackageName());
            this.answerButtonsTextView[j] = mainActivity.findViewById(textViewId);
            this.answerButtons[j] = mainActivity.findViewById(imageViewId);
        }

        this.exchangeIndexes = exchangeIndexes;
        this.mainActivity = mainActivity;
    }

    public void startConversation()
    //Starts this conversation with the NPC
    {
        //Setting the scroll position of scrollable views back to (0,0)
        mainActivity.findViewById(R.id.answer_scrollview).scrollTo(0,0);
        mainActivity.findViewById(R.id.dialogue_scrollview).scrollTo(0,0);

        hintImage.setVisibility(View.GONE);     //hiding the hintImage
        exchangeCounter = 0;
        showConversationElements();
        exchange = new Exchange(exchangeIndexes[0], mainActivity, this);
        //Depending on the character, we give the the view the appropriate image
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
        //Setting the scroll position of scrollable views back to (0,0)
        mainActivity.findViewById(R.id.answer_scrollview).scrollTo(0,0);
        mainActivity.findViewById(R.id.dialogue_scrollview).scrollTo(0,0);

        hintImage.setVisibility(View.GONE);         //hiding the hintImage
        exchangeCounter++;
        if(exchangeIndexes.length > exchangeCounter)            //If this is not the last exchange in this conversation
        {
            exchange = new Exchange(exchangeIndexes[exchangeCounter], mainActivity, this);
        }
        else        //If this is the last exchange in the conversation
        {
            if(name.equals("Niels"))
            {
                mainActivity.setTalkedToNiels(true);
            }
            else if (name.equals("Baker"))
            {
                mainActivity.setGotBread(true);
            }
            else if(name.equals("Clerk"))
            {
                mainActivity.setGotMilk(true);
            }
            hideConversationElements();
            mainActivity.getdPad().showDPad();
        }
    }

    public void hideConversationElements(){
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            answerButtons[j].setVisibility(View.GONE);
            answerButtonsTextView[j].setVisibility(View.GONE);
        }
        npcDialogueView.setVisibility(View.GONE);
        hintImage.setVisibility(View.GONE);
        dialogueText.setVisibility(View.GONE);
        answerText.setVisibility(View.GONE);
        conversationBlur.setVisibility(View.GONE);
        conversationBackground.setVisibility(View.GONE);
        answerTextField.setVisibility(View.GONE);
        speakerButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        exitButton.setVisibility(View.GONE);
        dialogueScrollIndicator.setVisibility(View.GONE);
        answerScrollIndicator.setVisibility(View.GONE);
    }

    public void showConversationElements(){
        for (int j = 0; j < answerButtonCount; j++) {
            String number = Integer.toString(j);
            String viewText = "answer_button_text_" + number;
            String imageView = "answer_button_" + number;
            int textViewId = mainActivity.getResources().getIdentifier(viewText, "id", mainActivity.getPackageName());
            int imageViewId = mainActivity.getResources().getIdentifier(imageView, "id", mainActivity.getPackageName());
            this.answerButtonsTextView[j] = mainActivity.findViewById(textViewId);
            this.answerButtons[j]= mainActivity.findViewById(imageViewId);
        }
        for (int j = 0; j < answerButtonsTextView.length ; j++) {
            answerButtons[j].setVisibility(View.VISIBLE);
            answerButtonsTextView[j].setVisibility(View.VISIBLE);
        }
        npcDialogueView.setVisibility(View.VISIBLE);
        dialogueText.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.VISIBLE);
        conversationBlur.setVisibility(View.VISIBLE);
        conversationBackground.setVisibility(View.VISIBLE);
        answerTextField.setVisibility(View.VISIBLE);
        speakerButton.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        exitButton.setVisibility(View.VISIBLE);
        dialogueScrollIndicator.setVisibility(View.VISIBLE);
        answerScrollIndicator.setVisibility(View.VISIBLE);
        //Hiding DPad, to be moved later
    }
    //------------------------------------------------------------------------
    //Getters
    public ImageView getHintImage() { return hintImage; }

    public ImageView[] getAnswerButtons() { return answerButtons; }

    public Exchange getCurrentExchange() { return exchange; }

    public int getCurrentExchangeID() {
        return exchangeIndexes[exchangeCounter];
    }
}
