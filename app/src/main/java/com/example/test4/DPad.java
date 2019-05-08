package com.example.test4;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

class DPad {
    private int moveDist = 100;
    private ImageView background;
    private float moveX;
    private float moveY;
    Button upButton;
    Button downButton;
    Button leftButton;
    Button rightButton;

    DPad (ImageView background, MainActivity mainActivity){

        moveX =  background.getX();
        moveY =  background.getY();
        this.background = background;
        /*upButton = mainActivity.findViewById(R.id.up_button);
        downButton = mainActivity.findViewById(R.id.down_button);
        leftButton = mainActivity.findViewById(R.id.left_button);
        rightButton = mainActivity.findViewById(R.id.right_button);*/

        //Here we are setting a specific location for the character, this needs to be updated once we have a functional game world
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
        animation.setDuration(0);
        animation.start();
        animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
        animation.setDuration(200);
        animation.start();
    }
    public void hideButtons()
    {

    }
    public void moveUp (){
        moveY += moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
            animation.setDuration(200);
            animation.start();
    }

    public void moveDown (){
        moveY -= moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
            animation.setDuration(200);
            animation.start();

    }

    public void moveLeft (){
        moveX += moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }

    public void moveRight (){
        moveX -= moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }


}
