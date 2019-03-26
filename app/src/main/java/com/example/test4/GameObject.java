package com.example.test4;

import android.media.Image;

class GameObject extends Instance {
    private int x;
    private int y;
    private Image[] image = new Image[10];

    GameObject(){}
    GameObject(int x, int y, Image[] image){
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void changeImage(Image newImage){

    }
}
