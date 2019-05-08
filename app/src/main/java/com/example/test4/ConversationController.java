package com.example.test4;

import android.view.View;
import android.widget.ImageView;

public class ConversationController extends Instance{

    private Exchange exchanges;
    private GameObject background;
    //private ImageView backButton;
    private int currentExchangeID;
    private OurButton continueButton;
    MainActivity mainActivity = new MainActivity();
    ConversationController(){}

    public void showExchange(){

    }

    public void exitConversation(){
        mainActivity.getHintImage().setVisibility(View.GONE);
        mainActivity.getAnswerText().setVisibility(View.GONE);
        mainActivity.getDialogueText().setVisibility(View.GONE);
    }
}
