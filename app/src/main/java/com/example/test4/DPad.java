package com.example.test4;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

class DPad {
    private int moveDist;
    private ImageView background;
    private float moveX;
    private float moveY;
    private int startX;
    private int startY;
    //the 32 is from the map border, and the 10 is from the image view placement
    private int xCorrection = 32 + 10;
    private int yCorrection = 32;
    Button upButton;
    Button downButton;
    Button leftButton;
    Button rightButton;

    DPad (ImageView background, MainActivity mainActivity){

        moveX =  background.getX();
        moveY =  background.getY();
        this.background = background;

        //these numbers don't exactly match the calculations, but it is as close as i could get
        moveDist = dpToPx(98);
        startX = dpToPx(-1344 + xCorrection);
        startY = dpToPx(-864 + yCorrection);
        /*upButton = mainActivity.findViewById(R.id.up_button);
        downButton = mainActivity.findViewById(R.id.down_button);
        leftButton = mainActivity.findViewById(R.id.left_button);
        rightButton = mainActivity.findViewById(R.id.right_button);*/

        //Here we are setting a specific location for the character, this needs to be updated once we have a functional game world
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), startY);
        animation.setDuration(0);
        animation.start();
        animation = ObjectAnimator.ofFloat(background, "X", background.getX(), startX);
        animation.setDuration(0);
        animation.start();
    }
    public void hideButtons()
    {

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void moveUp (){
        moveY = background.getY() + moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
            animation.setDuration(200);
            animation.start();
    }

    public void moveDown (){
        moveY = background.getY() - moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
            animation.setDuration(200);
            animation.start();

    }

    public void moveLeft (){
        moveX = background.getX() + moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }

    public void moveRight (){
        moveX = background.getX() - moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }


}
