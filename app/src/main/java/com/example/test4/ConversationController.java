package com.example.test4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationController extends Instance{

    private Exchange exchanges;
    private GameObject background;
    //private ImageView backButton;
    private int currentExchangeID;
    private OurButton continueButton;

    private int sizeSix = 6;

    private ImageView npcDialogueView;      //Image of the character you are having the conversation with
    private ImageView hintImage;            //Image view to show the hint of a word
    private TextView dialogueText;          //Text view to hold the text of the NPC
    private TextView answerText;            //Text view to hold the text of the user
    private ImageView conversationLayout;     //Image view to show the background of a conversation
    private ImageView answerTextField;      //Image where the answer is displayed on
    private ImageView speakerButton;        //Clickable image to play the sound
    private ImageView submitButton;        //Clickable image to submit the answer
    private ImageView exitButton;           //Clickable image to exit the conversation
    private TextView[] answerButtonsTextView = new TextView[sizeSix]; //List of the answer choices displayed
    private ImageView[] answerButtons = new ImageView[sizeSix];   //List of Images where the answer choices are displayed on

    ConversationController(MainActivity mainActivity){
        this.npcDialogueView = mainActivity.findViewById(R.id.npc_dialogue_view);
        this.hintImage = mainActivity.findViewById(R.id.hint_img);
        this.dialogueText = mainActivity.findViewById(R.id.dialogue_text);
        this.answerText = mainActivity.findViewById(R.id.answer_text);
        this.conversationLayout = mainActivity.findViewById(R.id.conversation_background);
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

    }

    public void showExchange(){

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
        getConversationLayout().setVisibility(View.GONE);
        getAnswerTextField().setVisibility(View.GONE);
        getSpeakerButton().setVisibility(View.GONE);
        getSubmitButton().setVisibility(View.GONE);
        getExitButton().setVisibility(View.GONE);


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

    public ImageView getConversationLayout() {
        return conversationLayout;
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
}
