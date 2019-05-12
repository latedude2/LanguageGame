package com.example.test4;

import android.media.Image;

class Character extends WorldElement {
    int portraitID;
    String name;
    private ConversationController[] conversations;
    private int[] exchanges;
    private int conversationIndex = 0;
    private MainActivity mainActivity;

    Character(String name, int portraitID, int gridX, int gridY, ConversationController[] conversations, MainActivity mainActivity){
        this.setXGrid(gridX);
        this.setYGrid(gridY);
        this.name = name;
        this.portraitID = portraitID;
        this.conversations = conversations;
        this.mainActivity = mainActivity;
    }
    void startConversation()
    {
        mainActivity.characterTalkingToYou = this;
        if(mainActivity.gotMilk && mainActivity.gotBread && name.equals("Niels"))
        {
            conversationIndex = 1;
            conversations[1].startConversation();
        }
        else
        {
            conversations[0].startConversation();
        }
    }
    public ConversationController getCurrentConversationController()
    {
        return conversations[conversationIndex];
    }

}
