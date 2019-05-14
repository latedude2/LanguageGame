package com.example.test4;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;

import static com.example.test4.DPad.dpToPx;

public class MainActivity extends Activity {

    //Name the variables (ImageViews, TextViews, Buttons) as their id to avoid confusion. Thank you.
    private ImageView worldView;        //Image view to show the map
    private ImageView worldBackground;         //Image view to show the background of the workd part
    private DPad dPad;                      //Controls for walking
    private ImageView hintImage;            //Image view to show the hint of a word

    private Character[] characters = new Character[4];
    private Portal[] portals = new Portal[6];


    private int moveDist = 0;

    public boolean talkedToNiels = false;
    public boolean gotBread = false;
    public boolean gotMilk = false;
    public boolean answer_scrollable = false;
    public boolean dialogue_scrollable = false;

    Character characterTalkingToYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        setupWorld();

        dPad.showDPad();
        loadConverstationCharacterImage();
    }

    public void setupWorld(){
        worldView = findViewById(R.id.world_view);
        dPad = new DPad(worldView, this);
        createCharacters();
        createPortals();
        setSizeForAnswerScrollView();
        applyFonts();

        ImageView imageView = findViewById(R.id.world_view);
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) imageView.getLayoutParams();
        params.width = 64 + moveDist * 38;
        params.height = 64 + moveDist * 20;
        // existing height is ok as is, no need to edit it
        imageView.setLayoutParams(params);

        int idOfMap = getResources().getIdentifier("map", "drawable", getPackageName());
        worldView.setImageResource(idOfMap);

        worldBackground = findViewById(R.id.d_pad_background);
        int idOfBackground = getResources().getIdentifier("background_world", "drawable", getPackageName());
        worldBackground.setImageResource(idOfBackground);

        dPad.hideDPad();
    }
    private void createCharacters()
    {
        ConversationController[] conversations = new ConversationController[2];
        int[] exchanges = {0, 1, 2};
        conversations[0] = new ConversationController(exchanges , this, "Niels");
        exchanges = new int[] {11, 12, 13, 14};
        conversations[1] = new ConversationController(exchanges , this, "Niels");
        int uncleID = getResources().getIdentifier("big_niels", "drawable", getPackageName());
        characters[0] = new Character("Niels", uncleID, 30, 16, conversations, this);

        conversations = new ConversationController[1];
        exchanges = new int[] {3,4};
        conversations[0] = new ConversationController(exchanges , this, "Old");
        int oldManID = getResources().getIdentifier("big_old", "drawable", getPackageName());
        characters[1] = new Character("Old", oldManID, 14, 14, conversations, this);

        conversations = new ConversationController[1];
        exchanges = new int[] {5, 6, 7};
        conversations[0] = new ConversationController(exchanges , this, "Clerk");
        int clerkID = getResources().getIdentifier("big_clerk", "drawable", getPackageName());
        characters[2] = new Character("Clerk", clerkID, 27, 4, conversations, this);

        conversations = new ConversationController[1];
        exchanges = new int[] {8, 9, 10};
        conversations[0] = new ConversationController(exchanges , this, "Baker");
        int bakerID = getResources().getIdentifier("big_baker", "drawable", getPackageName());
        characters[3] = new Character("Baker", bakerID, 33, 4, conversations, this);


    }
    private void createPortals()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        moveDist = width/4;

        //portal outside niels home
        int portalX = 16;
        int portalY = 5;
        int newGridX = 31;
        int newGridY = 17;
        int offsetX = (newGridX - portalX) * moveDist;
        int offsetY = (newGridY - (portalY + 1)) * moveDist;

        portals[0] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);
        //portal inside niels home
        portalX = 31;
        portalY = 18;
        newGridX = 16;
        newGridY = 6;
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

        //right door to shop
        portalX = 4;
        portalY = 10;
        newGridX = 26;
        newGridY = 6;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY + 1)) * moveDist;
        portals[4] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        //right door exit from shop
        portalX = 26;
        portalY = 7;
        newGridX = 4;
        newGridY = 11;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - (portalY - 1)) * moveDist;
        portals[5] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);
    }
    public Portal getPortalAt(int x, int y)
    //Returns portal located at given coordinates
    {
        for(int i = 0; i < 6; i++)
        {
            if(portals[i].getXGrid() == x && portals[i].getYGrid() == y)
            {
                return portals[i];
            }
        }
        throw new Error("Portal not found");
    }
    public Character getCharacterAt(int x, int y)
    //Returns character located at given coordinates
    {
        for(int i = 0; i < 4; i++)
        {
            if(characters[i].getXGrid() == x && characters[i].getYGrid() == y)
            {
                return characters[i];
            }
        }
        throw new Error("Character not found");
    }
    public DPad getdPad()
    {
        return dPad;
    }
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
        characterTalkingToYou.getCurrentConversationController().getCurrentExchange().addAnswer(view);
    }

    public void onSubmitClick(View view) {
        characterTalkingToYou.getCurrentConversationController().getCurrentExchange().submitAnswer(view);
    }

    public void onSoundViewClick(View v) {
        String audioFileName = "sentence" + characterTalkingToYou.getCurrentConversationController().getCurrentExchangeID();
        final int idOfAudioFile = getResources().getIdentifier(audioFileName, "raw", getPackageName());
        ImageView speaker_button = findViewById(R.id.speaker_button);
        characterTalkingToYou.getCurrentConversationController().getCurrentExchange().sentencePlay(speaker_button, idOfAudioFile);
    }

    public void exitConversation(View view) {
        characterTalkingToYou.getCurrentConversationController().hideConversationElements();
        dPad.showDPad();
    }

    /*
    public void loadConversation(){
        for (int i = 0; i < characters.length; i++) {
            characters[i].showConversation();
        }
    }
    */
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

    private void setSizeForAnswerScrollView(){
        ImageView answer_text_field = findViewById(R.id.answer_text_field);
        ScrollView answer_scrollview = findViewById(R.id.answer_scrollview);
        LinearLayout answer_LL = findViewById(R.id.answer_LL);

        answer_scrollview.getLayoutParams().height = answer_text_field.getHeight();
    }

    private void applyFonts() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/MerchantCopyMod.ttf");

        TextView[] toChangeFont = {findViewById(R.id.answer_text),
                findViewById(R.id.dialogue_text),
                findViewById(R.id.answer_button_text_0),
                findViewById(R.id.answer_button_text_1),
                findViewById(R.id.answer_button_text_2),
                findViewById(R.id.answer_button_text_3),
                findViewById(R.id.answer_button_text_4),
                findViewById(R.id.answer_button_text_5)};

        for (TextView t : toChangeFont) {
            t.setTypeface(font);
        }
    }
}