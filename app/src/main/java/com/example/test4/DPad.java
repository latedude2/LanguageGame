package com.example.test4;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

class DPad {

    private int moveDist = 50;

    private ImageView background;

    DPad (ImageView background){
        this.background = background;
    }

    private float moveX =  background.getX();
    private float moveY =  background.getY();



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
