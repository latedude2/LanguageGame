package com.example.test4;

import android.widget.ImageView;

class Portal extends WorldElement {

    ImageView map;


    public Portal (ImageView map){
        this.map = map;
    }


    public void moveMap (int newXDisplacement, int newYDisplacement){
        map.setX(newXDisplacement);
        map.setY(newYDisplacement);
    }



}
