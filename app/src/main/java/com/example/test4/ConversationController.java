package com.example.test4;

import android.view.View;

public class ConversationController extends Instance{

    private Exchange exchanges;
    private GameObject background;
    private OurButton backButton;
    private int currentExchangeID;
    private OurButton continueButton;
    MainActivity mainActivity;

    public void showExchange(){

    }

    public void exitConversation(){
        mainActivity.getExiteButton().setImageResource(R.drawable.exit_button);
        mainActivity.getExiteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (int i = 0; i < mainActivity.getAnswerButtonsTextView().length ; i++) {
                    mainActivity.getAnswerButtonsTextView()[i].setVisibility(View.GONE);
                }*/
                mainActivity.getHintImage().setVisibility(View.GONE);
                mainActivity.getAnswerText().setVisibility(View.GONE);
                mainActivity.getDialogueText().setVisibility(View.GONE);

            }
        });
    }
}
