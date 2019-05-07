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
                //mainActivity.
            }
        });
    }
}
