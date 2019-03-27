package com.example.test4;

public class PlayerObject extends GameObject {

    public boolean isMoving;

    public PlayerObject(boolean isMoving){
        this.isMoving = isMoving;
    }

    public void Animate(){

    }

    public void MoveLeft(){

    }

    public WorldElement CheckLeftTile(){
        return null;
    }
    
    public void MoveRight() {

    }

    public WorldElement CheckRightTile() {
        return null;
    }

    public void MoveUp() {

    }

    public WorldElement CheckUpTile() {
        return null;
    }

    public void MoveDown() {

    }

    public WorldElement CheckDownTile() {
        return null;
    }
}