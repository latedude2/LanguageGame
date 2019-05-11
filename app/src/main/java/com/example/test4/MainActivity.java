package com.example.test4;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import static com.example.test4.DPad.dpToPx;

public class MainActivity extends Activity {

    //Name the variables (ImageViews, TextViews, Buttons) as their id to avoid confusion. Thank you.

    private ImageView worldView;        //Image view to show the map
    private ImageView conversationBack;     //Image view to show the background of a conversation
    private ImageView worldBackground;         //Image view to show the background of the workd part
    private DPad dPad;                      //Controls for walking
    private TextView answerText;            //Text view to hold the text of the user
    private TextView dialogueText;          //Text view to hold the text of the NPC
    private ImageView hintImage;            //Image view to show the hint of a word

    private int uncleID;
    private int oldManID;
    private int bakerID;
    private int clerkID;

    private Exchange exchange;
    private TextView[] answerButtonsTextView = new TextView[6];
    private ImageView speaker_button;
    private ImageView submit_button;
    private AnimationDrawable pressing_submit;
    private Character[] characters = new Character[4];
    private Portal[] portals = new Portal[6];

    //private FileRead file;
    private ConversationController conversationController;
    private Instance instance;

    private int moveDist = 0;

    int exchangeID;
    int exchangeIndex = 4; //index which counts which exchange it is currently

    private FileRead fileRead; //creates the file object for all the Strings to be created there

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        setupWorld();



        loadConversation();
        dPad.hideDPad();

        //loadExchange(this.exchangeIndex);
        //loadExchangeTwo();
        loadConverstationCharacterImage();

    }

    public void setupWorld(){
        worldView = findViewById(R.id.world_view);
        dPad = new DPad(worldView, this);
        uncleID = getResources().getIdentifier("big_niels", "drawable", getPackageName());
        characters[0] = new Character(uncleID, 26, 13, new int[2], this);
        characters[0].setConversation(new int[] {0,4});
        oldManID = getResources().getIdentifier("big_old", "drawable", getPackageName());
        characters[1] = new Character(oldManID, 11, 12, new int[1], this);
        characters[1].setConversation(new int[]{1});
        clerkID = getResources().getIdentifier("big_clerk", "drawable", getPackageName());
        characters[2] = new Character(clerkID, 23, 3, new int[1], this);
        characters[2].setConversation(new int[]{2});
        bakerID = getResources().getIdentifier("big_baker", "drawable", getPackageName());
        characters[3] = new Character(bakerID, 29, 3, new int[1], this);
        characters[3].setConversation(new int[]{3});

        createPortals();

        ImageView imageView = findViewById(R.id.world_view);
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) imageView.getLayoutParams();
        params.width = 64 + moveDist * 32;
        params.height = 64 + moveDist * 16;
// existing height is ok as is, no need to edit it
        imageView.setLayoutParams(params);

        int idOfMap = getResources().getIdentifier("map", "drawable", getPackageName());
        worldView.setImageResource(idOfMap);

        worldBackground = findViewById(R.id.d_pad_background);
        int idOfBackground = getResources().getIdentifier("background_world", "drawable", getPackageName());
        worldBackground.setImageResource(idOfBackground);

        dPad.hideDPad();
        /*conversationBack = findViewById(R.id.conversation_background);
        int idOfBackground = getResources().getIdentifier("background_convo", "drawable", getPackageName());
        conversationBack.setImageResource(idOfBackground);*/

        /*submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);*/
    }
    private void createPortals()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        moveDist = width/4;

        //portal outside niels home
        int portalX = 13;
        int portalY = 4;
        int newGridX = 27;
        int newGridY = 15;
        int offsetX = (newGridX - portalX) * moveDist;
        int offsetY = (newGridY - (portalY + 1)) * moveDist;

        portals[0] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);
        //portal inside niels home
        portalX = 27;
        portalY = 16;
        newGridX = 13;
        newGridY = 5;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY - 1)) * moveDist;
        portals[1] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        //left door to shop
        portalX = 3;
        portalY = 10;
        newGridX = 25;
        newGridY = 6;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY + 1)) * moveDist;
        portals[2] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        //left door exit from shop
        portalX = 25;
        portalY = 7;
        newGridX = 3;
        newGridY = 11;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY - 1)) * moveDist;
        portals[3] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 4;
        portalY = 10;
        newGridX = 26;
        newGridY = 6;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY + 1)) * moveDist;
        portals[4] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 26;
        portalY = 7;
        newGridX = 4;
        newGridY = 11;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY - 1)) * moveDist;
        portals[5] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);
    }
    public Portal getPortalAt(int x, int y)
    {
        for(int i = 0; i < 6; i++)
        {
            if(portals[i].getGridX() == x && portals[i].getGridY() == y)
            {
                return portals[i];
            }
        }
        throw new Error("Portal not found");
    }
    //loads all the elements of the exchange view
    /*public void loadExchange(int exchangeIndex)
    {
        stream(exchangeIndex);
        getFile().read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        setExchange(new Exchange(getFile().getAnswerText(), getFile().getQuestionText(), getFile().getAllAnswers(),exchangeIndex, this));
        //conversationController = new ConversationController()
        getDialoguetext().setText(getExchange().checkHint());
        getDialoguetext().setMovementMethod(LinkMovementMethod.getInstance());


        getAnswerText().setText(getExchange().checkGap());

        for (int j = 0; j < answerButtonsTextView.length; j++){
            getAnswerButtonsTextView()[j].setText(getExchange().takeAnswers(j));
        }
        //onSoundViewClick();
        getExchange().resetSelectedAnswers();

    }*/
    public void loadConverstationCharacterImage() {
        ImageView imageView = findViewById(R.id.npc_dialogue_view);
        imageView.setImageResource(R.drawable.big_baker);
    }

    public void move_characterUp (final View v)
    {
        dPad.moveUp();
        disableDpadFor();
    }

    public void move_characterDown (final View v)
    {
        dPad.moveDown();
        disableDpadFor();
    }

    public void movePlayerLeft(final View v)
    {
        dPad.moveLeft();
        disableDpadFor();
    }

    public void move_characterRight (final View v)
    {
        dPad.moveRight();
        disableDpadFor();
    }

    public ImageView getHintImage() {
        hintImage = findViewById(R.id.hint_img);
        return hintImage;
    }

    public void answerClick(View view) {
        exchange.addAnswer(view);

    }

    public void onSubmitClick(View view) {
        exchange.submitAnswer(view);
    }

    public void onSoundViewClick() {
        String audioFileName = "sentence" + exchangeIndex;
        final int idOfAudioFile = getResources().getIdentifier(audioFileName, "raw", getPackageName());
        exchange.sentencePlay(speaker_button, idOfAudioFile);
    }


    public void exitConversation(View view) {
        conversationController = new ConversationController(this);
        //dPad = new DPad(worldView, this);
        conversationController.hideConversationElements();
        dPad.showDPad();
    }

    public void startConversation(View view) {
        conversationController = new ConversationController(this);
        //dPad = new DPad(worldView,this);
        conversationController.showConversationElements();
        dPad.hideDPad();
    }

    public void loadConversation(){
        for (int i = 0; i < characters.length; i++) {
            characters[i].showConversation();
        }
    }

    public int getOldManID() {
        return oldManID;
    }

    public int getClerkID() {
        return clerkID;
    }

    public int getBakerID() {
        return bakerID;
    }

    public int getUncleID() {
        return uncleID;
    }
    public void disableDpadFor()
    {
        final View v = findViewById(R.id.up_button);
        v.setEnabled(false);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setEnabled(true);
            }
        }, dPad.getAnimationLength());
        final View v2 = findViewById(R.id.down_button);
        v2.setEnabled(false);
        v2.postDelayed(new Runnable() {
            @Override
            public void run() {
                v2.setEnabled(true);
            }
        }, dPad.getAnimationLength());
        final View v3 = findViewById(R.id.left_button);
        v3.setEnabled(false);
        v3.postDelayed(new Runnable() {
            @Override
            public void run() {
                v3.setEnabled(true);
            }
        }, dPad.getAnimationLength());
        final View v4 = findViewById(R.id.right_button);
        v4.setEnabled(false);
        v4.postDelayed(new Runnable() {
            @Override
            public void run() {
                v4.setEnabled(true);
            }
        }, dPad.getAnimationLength());
    }
}