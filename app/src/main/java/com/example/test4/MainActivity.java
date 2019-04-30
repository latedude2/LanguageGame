package com.example.test4;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends Activity {

    //public ImageView img;
    private ImageView background;
    private DPad dPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = (ImageView) findViewById(R.id.world_view);
        dPad = new DPad(background);

        int i = 0;
        String index = Integer.toString(i); //use if it complains about using integer in the String in the following line
        String id = "R.raw.exchange" + index;
        int textFileID = Integer.valueOf(id);
        InputStream inputStream = this.getResources().openRawResource(textFileID);
        //FileRead file = new FileRead(i, inputStream);
        //Exchange exchange = new Exchange(file, file.getAnswerText(), file.getQuestionText(), file.getHintText(), file.getPossibleAnswers(), file.getGapText(), file.getCorrectAnswers());
    }


    public void move_characterUp (View v){
        dPad.moveUp();
    }

    public void move_characterDown (View v){
        dPad.moveDown();
    }

    public void move_characterLeft (View v){
        dPad.moveLeft();
    }

    public void move_characterRight (View v){
        dPad.moveRight();
    }
}
