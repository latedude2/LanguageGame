package com.example.test4;


import android.view.View;
import android.widget.ImageView;

public class Instance{
    //private MainActivity mainActivity;
    private DPad dPad;
    private int currentExchange = 0;

    private boolean isActive;
    Instance (){
    }
    Instance(boolean isActive){
        this.isActive = isActive;
    }

    void Update(){

    }

    public int getCurrentExchange() {
        return currentExchange;
    }

}
