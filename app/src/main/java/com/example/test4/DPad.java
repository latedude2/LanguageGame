package com.example.test4;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

class DPad {

    private int moveDist = 50;

    private ImageView background;
    private float moveX;
    private float moveY;

    DPad(){}
    DPad (ImageView background){
        moveX =  background.getX();
        moveY =  background.getY();
        this.background = background;
        /*
        ObjectAnimator animation = ObjectAnimator.ofFloat(background, "y", background.getY(), moveY);
        animation.setDuration(0);
        animation.start();
        animation = ObjectAnimator.ofFloat(background, "X", background.getX(), moveX);
        animation.setDuration(200);
        animation.start();
        */
    }

    public void showImage(ImageView imageView, int id)
    {
        imageView.setImageResource(id);
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
