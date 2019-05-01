package com.example.test4;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    private TextView dialoguetext;
    private ImageView hintImage;

    private String question;
    int i = 0; //index which counts which exchange it is currently

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dialoguetext = (TextView) findViewById(R.id.dialogue_text);
        hintImage = (ImageView) findViewById(R.id.hint_img);
        //hintImage.setImageResource(0);

        background = (ImageView) findViewById(R.id.world_view);
        dPad = new DPad(background);

        //TO BE PUT INTO OnClickListener()
        String index = Integer.toString(i); //use if it complains about using integer in the String in the following line
        String id = "exchange" + index; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(id,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        FileRead file = new FileRead(i, inputStream); //creates the file object for all the Strings to be created there
        file.read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        Exchange exchange = new Exchange(file.getAnswerText(), file.getQuestionText(), file.getHintText(), file.getAllAnswers(), file.getGapText(), file.getCorrectAnswers());
        dialoguetext.setText(exchange.clickHint());
        dialoguetext.setMovementMethod(LinkMovementMethod.getInstance());
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

    public ImageView getHintImage() {
        hintImage.setImageResource(R.drawable.background);
        return hintImage;
    }
}
