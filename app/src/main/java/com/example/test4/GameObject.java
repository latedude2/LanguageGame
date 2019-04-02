package com.example.test4;

import android.media.Image;

class GameObject extends Instance {
    private int x;
    private int y;
    private int xGrid;
    private int yGrid;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setxGrid(int xGrid) {
        this.xGrid = xGrid;
    }

    public void setyGrid(int yGrid) {
        this.yGrid = yGrid;
    }

    public void changeImage(Image newImage){

    }

    public void disableGameobject(){

    }

}
