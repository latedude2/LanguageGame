package com.example.test4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //public ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*img = findViewById(R.id.hint_img);
        img.setImageResource(R.mipmap.tower);

        Button btn = findViewById(R.id.button);

        

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testMethod(v);
            }
        });*/
    }
    /*public void testMethod(View v)
    {
        if(img.getVisibility() == View.GONE)
            img.setVisibility(View.VISIBLE);
        else if(img.getVisibility() == View.VISIBLE)
            img.setVisibility(View.GONE);
    }*/
}
