package com.example.test4;

class Portal extends WorldElement {
    private int newXDisplacement;
    private int newYDisplacement;

    private Portal (int newXDisplacement, int newYDisplacement){
        this.newXDisplacement = newXDisplacement;
        this.newYDisplacement = newYDisplacement;
    }

    public int getNewXDisplacement() {
        return newXDisplacement;
    }

    public int getNewYDisplacement() {
        return newYDisplacement;
    }
    public void OpenScene(Scene sceneToOpen, int xDisp, int yDisp){}

}
