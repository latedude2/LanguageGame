package com.example.test4;

class Character extends GameObject {
    String name;
    private ConversationController[] conversations;
    private int conversationIndex = 0;
    private MainActivity mainActivity;

    Character(String name, int gridX, int gridY, ConversationController[] conversations, MainActivity mainActivity){
        this.setXGrid(gridX);
        this.setYGrid(gridY);
        this.name = name;
        this.conversations = conversations;
        this.mainActivity = mainActivity;
    }
    void startConversation()
    //Starts the current conversation
    {
        mainActivity.characterTalkingToYou = this;
        if(mainActivity.isGotMilk() && mainActivity.isGotBread() && name.equals("Niels"))
        {
            conversationIndex = 1;
            conversations[conversationIndex].startConversation();
        }
        else
        {
            conversations[conversationIndex].startConversation();
        }
    }
    //----------------------------------------------------------------------------------------------
    //Getters
    public ConversationController getCurrentConversationController()
    //returns the current conversation controller
    {
        return conversations[conversationIndex];
    }

}
