package com.example.test4;

import android.media.Image;
import android.widget.ImageView;

class GameObject extends Instance {
    private int x;
    private int y;
    private int xGrid;
    private int yGrid;


    GameObject(){}
    GameObject(int x, int y){
        this.x = x;
        this.y = y;
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
