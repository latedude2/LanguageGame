package com.example.test4;

import android.annotation.TargetApi;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
public class Instance extends AppCompatActivity {
    private boolean isActive;
    Instance (){}
    Instance(boolean isActive){
        this.isActive = isActive;
    }

    /*public void readData() {
        String expectedData = "Hello World from fileTest.txt!!!";

        Class clazz = FileOperationsTest.class;
        InputStream inputStream = clazz.getResourceAsStream("/data.txt");
        String data = readFromInputStream(inputStream);

        Assert.assertThat(data, containsString(expectedData));
    }*/

    void Update(){

    }
}
