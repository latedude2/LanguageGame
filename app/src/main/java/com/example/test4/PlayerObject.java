package com.example.test4;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class PlayerObject extends GameObject {

    private ImageView char_world_view;      // Image view to show player character

    public PlayerObject(MainActivity mainActivity){
        setXGrid(32);
        setYGrid(16);
        char_world_view = mainActivity.findViewById(R.id.char_world_view);
        int idOfPlayer = mainActivity.getResources().getIdentifier("main_character", "drawable", mainActivity.getPackageName());
        char_world_view.setImageResource(idOfPlayer);
    }

    public void moveDown()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_down);
        AnimationDrawable walking_down = (AnimationDrawable) char_world_view.getDrawable();
        walking_down.start();
        setYGrid(getYGrid() + 1);
    }
    public void moveLeft()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_left);
        AnimationDrawable walking_left = (AnimationDrawable) char_world_view.getDrawable();
        walking_left.start();
        setXGrid(getXGrid() - 1);
    }
    public void moveRight()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_right);
        AnimationDrawable walking_right = (AnimationDrawable) char_world_view.getDrawable();
        walking_right.start();
        setXGrid(getXGrid() + 1);
    }
    public void moveUp()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_up);
        AnimationDrawable walking_up = (AnimationDrawable) char_world_view.getDrawable();
        walking_up.start();
        setYGrid(getYGrid() - 1);
    }


}