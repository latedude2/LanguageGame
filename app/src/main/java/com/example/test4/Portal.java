package com.example.test4;

class Portal extends WorldElement {
    private int newXDisplacement;
    private int newYDisplacement;
    private int newXGrid;
    private int newYGrid;

    private Portal (int newXDisplacement, int newYDisplacement, int newXGrid, int newYGrid){
        this.newXDisplacement = newXDisplacement;
        this.newYDisplacement = newYDisplacement;
        this.newXGrid = newXGrid;
        this.newYGrid = newYGrid;
    }

    public int getNewXDisplacement() {
        return newXDisplacement;
    }

    public int getNewYDisplacement() {
        return newYDisplacement;
    }

    public int getNewXGrid() {
        return newXGrid;
    }

    public int getNewYGrid() {
        return newYGrid;
    }

    public void MoveMap (PlayerObject player){
        player.setX(newXDisplacement);
        player.setY(newYDisplacement);
    }

   public void MovePlayer (PlayerObject player){
        player.setxGrid(newXGrid);
        player.setyGrid(newYGrid);
   }

}
