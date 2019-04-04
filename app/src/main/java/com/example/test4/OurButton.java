package com.example.test4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

public class OurButton extends android.support.v7.widget.AppCompatButton {
    int index = 0;

    public OurButton(Context context) {
        super(context);
    }

    public int getIndex() {
        return index;
    }
}
