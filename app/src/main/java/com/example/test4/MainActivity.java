package com.example.test4;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends Activity {

    //Name the variables (ImageViews, TextViews, Buttons) as their id to avoid confusion. Thank you.

    private ImageView worldView;        //Image view to show the map
    private ImageView conversationBack;     //Image view to show the background of a conversation
    private ImageView worldBackground;         //Image view to show the background of the workd part
    private DPad dPad;                      //Controls for walking
    private TextView answerText;            //Text view to hold the text of the user
    private TextView dialoguetext;          //Text view to hold the text of the NPC
    private ImageView hintImage;            //Image view to show the hint of a word
    private ImageView char_world_wiev;      // Image view to show player character
    private ImageView d_pad_imageviev;

    //for the different walking animations
    private AnimationDrawable walking_up;
    private AnimationDrawable walking_down;
    private AnimationDrawable walking_left;
    private AnimationDrawable walking_right;

    //for the dpad pressed animations
    private AnimationDrawable pressing_up;
    private AnimationDrawable pressing_down;
    private AnimationDrawable pressing_left;
    private AnimationDrawable pressing_right;

    private Exchange exchange;
    private TextView[] answerButtonsTextView = new TextView[6];
    private ImageView speaker_button;
    private ImageView submit_button;
    private AnimationDrawable pressing_submit;

    private FileRead file;
    private ConversationController conversationController;
    private Instance instance;

    int exchangeIndex = 4; //index which counts which exchange it is currently

    int[] answerNum;

    char[][] mapTiles; //2D array for the map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        loadLayoutImage();

        dPad.hideDPad();
        loadExchange(this.exchangeIndex);
        //loadExchangeTwo();
        loadConverstationCharacterImage();

        loadMapStructure();


    }

    MainActivity(){}

    public void loadLayoutImage(){
        worldView = findViewById(R.id.world_view);

        dPad = new DPad(worldView, this);


        int idOfMap = getResources().getIdentifier("map", "drawable", getPackageName());
        worldView.setImageResource(idOfMap);

        worldBackground = findViewById(R.id.d_pad_background);
        int idOfBackground = getResources().getIdentifier("background_world", "drawable", getPackageName());
        worldBackground.setImageResource(idOfBackground);

        
        char_world_wiev = findViewById(R.id.char_world_view);
        int idOfPlayer = getResources().getIdentifier("main_character", "drawable", getPackageName());
        char_world_wiev.setImageResource(idOfPlayer);

        d_pad_imageviev = findViewById(R.id.d_pad_imageview);
        int idOfDpad = getResources().getIdentifier("dpad_base", "drawable", getPackageName());
        d_pad_imageviev.setImageResource(idOfDpad);
        
        dPad.hideDPad();

        /*conversationBack = findViewById(R.id.conversation_background);
        int idOfBackground = getResources().getIdentifier("background_convo", "drawable", getPackageName());
        conversationBack.setImageResource(idOfBackground);*/

        /*submit_button = findViewById(R.id.submit_button);
        submit_button.setImageResource(R.drawable.proceed_button);

        speaker_button = findViewById(R.id.speaker_button);
        speaker_button.setImageResource(R.drawable.speaker);*/
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

    public void move_characterUp (View v){
        char_world_wiev.setImageResource(R.drawable.walk_animation_up);
        walking_up = (AnimationDrawable) char_world_wiev.getDrawable();
        walking_up.start();

        d_pad_imageviev.setImageResource(R.drawable.dpad_press_up);
        pressing_up = (AnimationDrawable) d_pad_imageviev.getDrawable();
        pressing_up.start();

        dPad.moveUp();
    }

    public void move_characterDown (View v){
        char_world_wiev.setImageResource(R.drawable.walk_animation_down);
        walking_down = (AnimationDrawable) char_world_wiev.getDrawable();
        walking_down.start();

        d_pad_imageviev.setImageResource(R.drawable.dpad_press_down);
        pressing_down = (AnimationDrawable) d_pad_imageviev.getDrawable();
        pressing_down.start();

        dPad.moveDown();
        startConversation(v);
    }

    public void move_characterLeft (View v){
        char_world_wiev.setImageResource(R.drawable.walk_animation_left);
        walking_left = (AnimationDrawable) char_world_wiev.getDrawable();
        walking_left.start();

        d_pad_imageviev.setImageResource(R.drawable.dpad_press_left);
        pressing_left = (AnimationDrawable) d_pad_imageviev.getDrawable();
        pressing_left.start();

        dPad.moveLeft();

    }

    public void move_characterRight (View v){
        char_world_wiev.setImageResource(R.drawable.walk_animation_right);
        walking_right = (AnimationDrawable) char_world_wiev.getDrawable();
        walking_right.start();

        d_pad_imageviev.setImageResource(R.drawable.dpad_press_right);
        pressing_right = (AnimationDrawable) d_pad_imageviev.getDrawable();
        pressing_right.start();

        dPad.moveRight();
    }

    public ImageView getHintImage(){
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

    //creates the 2D array mapTiles, which holds the structure of the map
    public void loadMapStructure(){
        String idName = "map_structure"; //creates a String name of the file to use in the following line
        int idOfFile = getResources().getIdentifier(idName,"raw", getPackageName());
        InputStream inputStream = this.getResources().openRawResource(idOfFile);
        FileRead fileStructure = new FileRead(inputStream); //creates the file object for all the Strings to be created there
        mapTiles = fileStructure.readStructureChars(); //reads all of the chars in the structure and adds them to the 2D array
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