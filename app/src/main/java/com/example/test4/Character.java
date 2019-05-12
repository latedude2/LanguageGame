package com.example.test4;

import android.media.Image;

class Character extends WorldElement {
    private int portraitID;
    private ConversationController[] conversations;
    private int[] exchanges;
    private int conversationIndex = 0;
    private MainActivity mainActivity;

    Character(int portraitID, int gridX, int gridY, ConversationController[] conversations, MainActivity mainActivity){
        this.setXGrid(gridX);
        this.setYGrid(gridY);
        this.portraitID = portraitID;
        this.conversations = conversations;
        this.mainActivity = mainActivity;
    }
    void startConversation()
    {
        conversations[conversationIndex].startConversation();
    }
    public ConversationController getCurrentConversationController()
    {
        return conversations[conversationIndex];
    }

}
