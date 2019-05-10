package com.example.test4;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class PlayerObject extends GameObject {

    public boolean isMoving;
    private ImageView char_world_view;      // Image view to show player character
    //for the different walking animations
    private AnimationDrawable walking_up;
    private AnimationDrawable walking_down;
    private AnimationDrawable walking_left;
    private AnimationDrawable walking_right;

    public PlayerObject(MainActivity mainActivity){
        char_world_view = mainActivity.findViewById(R.id.char_world_view);
        int idOfPlayer = mainActivity.getResources().getIdentifier("main_character", "drawable", mainActivity.getPackageName());
        char_world_view.setImageResource(idOfPlayer);

    }

    public void moveDown()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_down);
        walking_down = (AnimationDrawable) char_world_view.getDrawable();
        walking_down.start();
    }
    public void moveLeft()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_left);
        walking_left = (AnimationDrawable) char_world_view.getDrawable();
        walking_left.start();
    }
    public void moveRight()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_right);
        walking_right = (AnimationDrawable) char_world_view.getDrawable();
        walking_right.start();
    }
    public void moveUp()
    {
        char_world_view.setImageResource(R.drawable.walk_animation_up);
        walking_up = (AnimationDrawable) char_world_view.getDrawable();
        walking_up.start();
    }
}