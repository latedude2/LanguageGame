package com.example.test4;

import android.media.Image;

class Character extends WorldElement {
    private int portraitID;
    private int currentConversationId;

    Character(int portraitID, int gridX, int gridY){
        this.setXGrid(gridX);
        this.setYGrid(gridY);
        this.portraitID = portraitID;
        this.currentConversationId = 0;
    }


    private void showConversation()
    {

    }
}
