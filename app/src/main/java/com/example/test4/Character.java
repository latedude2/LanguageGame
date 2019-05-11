package com.example.test4;

import android.media.Image;

class Character extends WorldElement {
    private int portraitID;
    private int[] conversation;
    private int[] exchanges;
    private int conversationID;
    private MainActivity mainActivity;
    private Exchange exchange;
    private int currentExchange;
    ConversationController conversationController;

    Character(int portraitID, int gridX, int gridY, int[] conversation, MainActivity mainActivity){
        this.setXGrid(gridX);
        this.setYGrid(gridY);
        this.portraitID = portraitID;
        this.conversation = conversation;
        this.mainActivity = mainActivity;
        conversationController = new ConversationController(getExchanges(), getConversationID(), mainActivity);
    }
    Character(){}

    public int getConversationID() {

        return conversationID;
    }

    public void showConversation()
    {
        conversationController.loadExchange();
    }

    public int[] getConversation() {
        return conversation;
    }

    public void setConversation(int[] conversation) {
        this.conversation = conversation;
    }

    public int[] getExchanges() {
        conversationID = conversationController.getConversationId();
        if (conversationID ==0)
            exchanges = new int[] {0,1,2};
        else if (conversationID == 1)
            exchanges = new int[] {3,4};
        else if (conversationID == 2)
            exchanges = new int[] {5,6,7};
        else if (conversationID == 3)
            exchanges = new int[] {8,9,10};
        else if (conversationID == 4){
            exchanges = new int[] {11,12,13,14};
        }
        return exchanges;
    }

    public void setExchanges(int[] exchanges) {
        this.exchanges = exchanges;
    }

    public void checkConversation(){

    }
}
