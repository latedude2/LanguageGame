package com.example.test4;

import android.media.Image;

class Character extends WorldElement {
    private String name;
    private int characterId;
    private Image portrait;
    private int currentConversationId;

    private Character(String name, int characterId, Image portrait, int currentConversationId){
        this.name = name;
        this.characterId = characterId;
        this.portrait = portrait;
        this.currentConversationId = currentConversationId;
    }

    private void showConversation(){}
}
