package com.example.test4;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class PlayerObject extends GameObject {

    private ImageView characterWorldView;      // Image view to show player character

    public PlayerObject(MainActivity mainActivity){
        setXGrid(32);
        setYGrid(16);
        characterWorldView = mainActivity.findViewById(R.id.char_world_view);
        int idOfPlayer = mainActivity.getResources().getIdentifier("main_character", "drawable", mainActivity.getPackageName());
        characterWorldView.setImageResource(idOfPlayer);
    }

    public void moveDown()
    {
        characterWorldView.setImageResource(R.drawable.walk_animation_down);
        AnimationDrawable walking_down = (AnimationDrawable) characterWorldView.getDrawable();
        walking_down.start();
        setYGrid(getYGrid() + 1);
    }
    public void moveLeft()
    {
        characterWorldView.setImageResource(R.drawable.walk_animation_left);
        AnimationDrawable walking_left = (AnimationDrawable) characterWorldView.getDrawable();
        walking_left.start();
        setXGrid(getXGrid() - 1);
    }
    public void moveRight()
    {
        characterWorldView.setImageResource(R.drawable.walk_animation_right);
        AnimationDrawable walking_right = (AnimationDrawable) characterWorldView.getDrawable();
        walking_right.start();
        setXGrid(getXGrid() + 1);
    }
    public void moveUp()
    {
        characterWorldView.setImageResource(R.drawable.walk_animation_up);
        AnimationDrawable walking_up = (AnimationDrawable) characterWorldView.getDrawable();
        walking_up.start();
        setYGrid(getYGrid() - 1);
    }


}