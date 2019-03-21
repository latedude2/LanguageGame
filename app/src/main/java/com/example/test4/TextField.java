package com.example.test4;

class TextField extends UIElement{
    private String text;
    TextField(String text){
        this.text = text;
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }
}
