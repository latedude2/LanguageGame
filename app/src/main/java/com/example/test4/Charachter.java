package com.example.test4;

import android.media.Image;

class Charachter extends Interactable {
    private String name;
    private int charachterId;
    private Image portrait;
    private int currentConversationId;

    private Charachter(String name, int charachterId, Image portrait, int currentConversationId){
        this.name = name;
        this.charachterId = charachterId;
        this.portrait = portrait;
        this.currentConversationId = currentConversationId;
    }

    private void showConversation(){}
}
