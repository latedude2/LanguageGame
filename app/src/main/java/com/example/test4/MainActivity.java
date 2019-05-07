package com.example.test4;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends Activity {

    //public ImageView img;
    private ImageView backgroundMap;
    private ImageView conversationBack;
    private DPad dPad;
    private DPad conversationBackground;

    private TextView answerText;

    private TextView dialoguetext;
    private ImageView hintImage;


    private Button[] answerButtons;

    int i = 6; //index which counts which exchange it is currently

    int idOfImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView exitButton = findViewById(R.id.exit_menu_button);


        ImageView shit = findViewById(R.id.hint_img);
        shit.setImageResource(R.drawable.maelk);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        makeMoveButtonGone();

        loadLayoutImage();
        loadExchange();

        loadConverstationCharacterImage();
    }

    public void makeMoveButtonGone(){
        Button upButton = findViewById(R.id.up_button);
        Button downButton = findViewById(R.id.down_button);
        Button leftButton = findViewById(R.id.left_button);
        Button rightButton = findViewById(R.id.right_button);
        upButton.setVisibility(View.GONE);
        downButton.setVisibility(View.GONE);
        leftButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);
    }

    public void loadLayoutImage(){
        //dPad = new DPad();

        backgroundMap = (ImageView) findViewById(R.id.world_view);

        dPad = new DPad(backgroundMap);

        conversationBack = (ImageView) findViewById(R.id.conversation_view);

        idOfImage = getResources().getIdentifier("map", "drawable", getPackageName());
        dPad.showImage(backgroundMap, idOfImage);

        idOfImage = getResources().getIdentifier("background", "drawable", getPackageName());
        dPad.showImage(conversationBack, idOfImage);


        ImageView submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        ImageView speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);
    }
    public void loadExchange(){
        //TO BE PUT INTO OnClickListener()
        String index = Integer.toString(i); //use if it complains about using integer in the String in the following line
        String id = "exchange" + index; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(id,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        FileRead file = new FileRead(i, inputStream); //creates the file object for all the Strings to be created there
        file.read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        Exchange exchange = new Exchange(file.getAnswerText(), file.getQuestionText(), file.getAllAnswers(), file.getGapText(), file.getCorrectAnswers(), this);
        dialoguetext = (TextView) findViewById(R.id.dialogue_text);
        dialoguetext.setText(exchange.checkHint());
        dialoguetext.setMovementMethod(LinkMovementMethod.getInstance());

        answerText = (TextView) findViewById(R.id.answer_text);
        answerText.setText(exchange.checkGap());

        exchange.checkAnswer();
    }

    public void loadConverstationCharacterImage(){
        ImageView imageView = findViewById(R.id.npc_dialogue_view);
        imageView.setImageResource(R.drawable.big_baker);
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

    public ImageView getHintImage(){
        hintImage = (ImageView) findViewById(R.id.hint_img);
        return hintImage;
    }

    public Button[] getButtons(){
        answerButtons = new Button[6];
        answerButtons[0].findViewById(R.id.answer_button_0);
        answerButtons[1].findViewById(R.id.answer_button_1);
        answerButtons[2].findViewById(R.id.answer_button_2);
        answerButtons[3].findViewById(R.id.answer_button_3);
        answerButtons[4].findViewById(R.id.answer_button_4);
        answerButtons[5].findViewById(R.id.answer_button_5);
        return answerButtons;
    }

}
