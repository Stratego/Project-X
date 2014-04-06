package com.uab.lis.rugby.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by adria on 30/03/14.
 */
public class Utils {
    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    public static Bitmap getBitmapFromAssets(Context cont, String filename){
        InputStream input=getInputStreamFromAssets(cont,filename);
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        return bitmap;
    }
    public static InputStream getInputStreamFromAssets(Context cont, String filename){
        InputStream input=null;

        try {
            input = cont.getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(input!=null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return input;
    }
    public static Drawable getDrawableFromAssets(Context ctx,String filename) throws IOException {
        Drawable d = Drawable.createFromStream(ctx.getAssets().open(filename), null);
        return d;
    }
}
