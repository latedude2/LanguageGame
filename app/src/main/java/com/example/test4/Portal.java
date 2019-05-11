package com.example.test4;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

class Portal extends WorldElement {

    ImageView map;
    private int gridX;
    private int gridY;
    private int offsetWorldX;
    private int offsetWorldY;
    private int destGridX;
    private int destGridY;

    public Portal (ImageView map, int portalGridX, int portalGridY, int offsetWorldX, int offsetWorldY, int gridX, int gridY){
        this.map = map;
        this.gridX = portalGridX;
        this.gridY = portalGridY;
        this.offsetWorldX = offsetWorldX;
        this.offsetWorldY = offsetWorldY;
        destGridX = gridX;
        destGridY = gridY;
    }
    public void teleport(PlayerObject player)
    {
        movePlayer(player);
        moveMap();
    }
    private void movePlayer(PlayerObject player)
    {
        player.setXGrid(destGridX);
        player.setYGrid(destGridY);
    }
    private void moveMap (){
        float moveY = map.getY() - offsetWorldY;
        ObjectAnimator animation = ObjectAnimator.ofFloat(map, "y", map.getY(), moveY);
        animation.setDuration(0);
        animation.start();

        float moveX = map.getX() - offsetWorldX;
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(map, "x", map.getX(), moveX);
        animation2.setDuration(0);
        animation2.start();
        /*
        map.setX(offsetWorldX);
        map.setY(destY);
        */
    }
    public int getGridX()
    {
        return gridX;
    }
    public int getGridY()
    {
        return gridY;
    }



}
