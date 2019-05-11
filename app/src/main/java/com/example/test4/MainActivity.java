package com.example.test4;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
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
    private TextView dialoguetext;          //Text view to hold the text of the NPC
    private ImageView hintImage;            //Image view to show the hint of a word

    private Exchange exchange;
    private TextView[] answerButtonsTextView = new TextView[6];
    private ImageView speaker_button;
    private ImageView submit_button;
    private AnimationDrawable pressing_submit;
    private Character[] characters = new Character[4];
    private Portal[] portals = new Portal[6];

    private FileRead file;
    private ConversationController conversationController;
    private Instance instance;

    int exchangeIndex = 4; //index which counts which exchange it is currently

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        setupWorld();


        dPad.hideDPad();
        loadExchange(this.exchangeIndex);
        //loadExchangeTwo();
        loadConverstationCharacterImage();

    }

    public void setupWorld(){
        worldView = findViewById(R.id.world_view);
        dPad = new DPad(worldView, this);
        characters[0] = new Character(getResources().getIdentifier("big_niels", "drawable", getPackageName()), 26, 13);
        characters[1] = new Character(getResources().getIdentifier("big_old", "drawable", getPackageName()), 11, 12);
        characters[2] = new Character(getResources().getIdentifier("big_clerk", "drawable", getPackageName()), 23, 3);
        characters[3] = new Character(getResources().getIdentifier("big_baker", "drawable", getPackageName()), 29, 3);

        createPortals();

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
        int moveDist = dpToPx(96);
        int portalX = 13;
        int portalY = 4;
        int newGridX = 28;
        int newGridY = 14;
        int offsetX = (newGridX - portalX) * moveDist;
        int offsetY = (newGridY - portalY) * moveDist;

        portals[0] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 28;
        portalY = 15;
        newGridX = 13;
        newGridY = 5;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - portalY) * moveDist;
        portals[1] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 3;
        portalY = 10;
        newGridX = 26;
        newGridY = 5;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - portalY) * moveDist;
        portals[2] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 26;
        portalY = 6;
        newGridX = 3;
        newGridY = 11;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - portalY) * moveDist;
        portals[3] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 4;
        portalY = 10;
        newGridX = 27;
        newGridY = 5;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - portalY) * moveDist;
        portals[4] = new Portal(worldView, portalX, portalY, offsetX, offsetY, newGridX , newGridY);

        portalX = 27;
        portalY = 6;
        newGridX = 4;
        newGridY = 11;
        offsetX = (newGridX - portalX) * moveDist;
        offsetY = (newGridY - portalY) * moveDist;
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
    public void loadExchange(int exchangeIndex)
    {
        String index = Integer.toString(exchangeIndex); //use if it complains about using integer in the String in the following line
        String id = "exchange" + index; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(id,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        file = new FileRead(inputStream); //creates the file object for all the Strings to be created there
        file.read();
        //creates exchange object which consists of all the Strings to be put in that one created exchange
        exchange = new Exchange(file.getAnswerText(), file.getQuestionText(), file.getAllAnswers(), this);
        dialoguetext = findViewById(R.id.dialogue_text);
        dialoguetext.setText(exchange.checkHint());
        dialoguetext.setMovementMethod(LinkMovementMethod.getInstance());

        answerText = findViewById(R.id.answer_text);
        answerText.setText(exchange.checkGap());

        for (int j = 0; j < answerButtonsTextView.length; j++){
            String number = Integer.toString(j);
            String viewText = "answer_button_text_" + number;
            int textViewId = getResources().getIdentifier(viewText, "id", getPackageName());
            answerButtonsTextView[j] = findViewById(textViewId);
            answerButtonsTextView[j].setText(exchange.takeAnswers(j));
        }
        //onSoundViewClick();
        exchange.resetSelectedAnswers();

    }
    public void loadConverstationCharacterImage(){
        ImageView imageView = findViewById(R.id.npc_dialogue_view);
        imageView.setImageResource(R.drawable.big_baker);
    }

    public void move_characterUp (View v)
    {
        dPad.moveUp();
    }

    public void move_characterDown (View v)
    {
        dPad.moveDown();
        //startConversation(v);
    }

    public void movePlayerLeft(View v)
    {
        dPad.moveLeft();
    }

    public void move_characterRight (View v)
    {
        dPad.moveRight();
    }

    public ImageView getHintImage()
    {
        hintImage = findViewById(R.id.hint_img);
        return hintImage;
    }
    public void answerClick(View view)
    {
        exchange.addAnswer(view);

    }

    public void onSubmitClick(View view)
    {
        exchange.submitAnswer(view);
    }

    public void onSoundViewClick()
    {
        String audioFileName = "sentence" + exchangeIndex;
        final int idOfAudioFile = getResources().getIdentifier(audioFileName, "raw", getPackageName());
        exchange.sentencePlay(speaker_button, idOfAudioFile);
    }



    public void exitConversation(View view){
        conversationController = new ConversationController(this);
        //dPad = new DPad(worldView, this);
        conversationController.hideConversationElements();
        dPad.showDPad();
    }

    public void startConversation(View view){
        conversationController = new ConversationController(this);
        //dPad = new DPad(worldView,this);
        conversationController.showConversationElements();
        dPad.hideDPad();
    }

    public void loadExchangeTwo(){
        conversationController = new ConversationController(this);
        conversationController.loadExchange(exchangeIndex);
    }
}