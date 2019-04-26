package com.example.test4;

import android.content.Context;
import android.content.res.AssetManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    /*try{
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();

        myParser.setInput("", null);

        int event = myParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String name = myParser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if(name.equals("text")){
                        text = myParser.getAttributeValue(null,"text");
                        System.out.println(text);
                    }

                    break;
            }
            event = myParser.next();
        }
    }catch (XmlPullParserException e){
            e.getDetail();
        }*/

    /*private Context mContext;

    public FileRead(Context context) {
        this.mContext = context;
    }

    public List<String> readLine(String path) {
        List<String> mLines = new ArrayList<>();

        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                mLines.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mLines;
    }*/
}
