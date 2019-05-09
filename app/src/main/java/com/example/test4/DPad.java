package com.example.test4;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

class DPad {
    private int moveDist;
    private ImageView worldView;
    private float moveX;
    private float moveY;
    private int startX;
    private int startY;
    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;
    private ImageView dPadBackground;       //Image of the area where the DPad is displayed
    private ImageView dPadImageView;        //To be changed later will be divided by 4


    DPad (ImageView worldView, MainActivity mainActivity){

        moveX =  worldView.getX();
        moveY =  worldView.getY();
        this.worldView = worldView;

        //these numbers don't exactly match the calculations, but it is as close as i could get
        moveDist = dpToPx(95);
        startX = dpToPx(-1290);
        startY = dpToPx(-810);

        //Here we are setting a specific location for the character, this needs to be updated once we have a functional game world
        ObjectAnimator animation = ObjectAnimator.ofFloat(worldView, "y", worldView.getY(), startY);
        animation.setDuration(0);
        animation.start();
        animation = ObjectAnimator.ofFloat(worldView, "X", worldView.getX(), startX);
        animation.setDuration(0);
        animation.start();

        //All DPad elements declared and set to their ImageView/Button
        //Buttons should be changed later to ImageViews
        upButton = mainActivity.findViewById(R.id.up_button);
        downButton = mainActivity.findViewById(R.id.down_button);
        leftButton = mainActivity.findViewById(R.id.left_button);
        rightButton = mainActivity.findViewById(R.id.right_button);
        dPadBackground = mainActivity.findViewById(R.id.d_pad_background);
        dPadImageView = mainActivity.findViewById(R.id.d_pad_imageview);

    }
    public void hideDPad()
    {
        upButton.setVisibility(View.GONE);
        downButton.setVisibility(View.GONE);
        leftButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);
        dPadImageView.setVisibility(View.GONE);
        dPadBackground.setVisibility(View.GONE);

    }

    public void showDPad(){
        upButton.setVisibility(View.VISIBLE);
        downButton.setVisibility(View.VISIBLE);
        leftButton.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
        dPadImageView.setVisibility(View.VISIBLE);
        dPadBackground.setVisibility(View.VISIBLE);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void moveUp (){
        moveY = worldView.getY() + moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(worldView, "y", worldView.getY(), moveY);
            animation.setDuration(200);
            animation.start();
    }

    public void moveDown (){
        moveY = worldView.getY() - moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(worldView, "y", worldView.getY(), moveY);
            animation.setDuration(200);
            animation.start();

    }

    public void moveLeft (){
        moveX = worldView.getX() + moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(worldView, "X", worldView.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }

    public void moveRight (){
        moveX = worldView.getX() - moveDist;
        ObjectAnimator animation = ObjectAnimator.ofFloat(worldView, "X", worldView.getX(), moveX);
            animation.setDuration(200);
            animation.start();
    }

    //Create getters for ImageViews when we are done.
}
