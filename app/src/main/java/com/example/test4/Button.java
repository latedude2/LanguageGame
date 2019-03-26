package com.example.test4;

class Button extends UIElement{
    private Callable method;
    public Button(Callable method){
        this.method = method;
    }
    boolean isClicked(){
        return true;
    }
    public void callMethod(Callable method){}
}
