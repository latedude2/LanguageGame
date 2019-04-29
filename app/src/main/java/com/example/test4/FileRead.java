package com.example.test4;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

    private String[] questionText;
    private String[] answerText;
    private String[] gapText;
    private String[] hintText;
    private String[] possibleAnswers;

    private int i = 0;

    public FileRead

    public void read() {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = this.getResources().openRawResource(R.raw.textfile);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((questionText[i] = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }
            textView.setText(stringBuffer);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
