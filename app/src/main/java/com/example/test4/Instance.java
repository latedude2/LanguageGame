package com.example.test4;

import android.annotation.TargetApi;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
public class Instance {
    private boolean isActive;
    Instance (){}
    Instance(boolean isActive){
        this.isActive = isActive;
    }

    void Update(){

    }
}
