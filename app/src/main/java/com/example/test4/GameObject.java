package com.example.test4;

class GameObject  {
    private int xGrid;
    private int yGrid;

    GameObject(){}

    //----------------------------------------------------------------------------------------------
    //Setters
    public void setXGrid(int xGrid) {
        this.xGrid = xGrid;
    }

    public void setYGrid(int yGrid) {
        this.yGrid = yGrid;
    }
    //----------------------------------------------------------------------------------------------
    //Getters

    public int getXGrid() { return xGrid; }

    public int getYGrid() { return yGrid; }
}
