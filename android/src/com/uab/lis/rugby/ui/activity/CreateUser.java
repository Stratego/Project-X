package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adria on 30/03/14.
 */
public class CreateUser extends Activity {
    private EditText nameUser;
    private EditText nameEquipo;
    private ViewPager escudo;
    private ViewPager camiseta;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        nameUser = (EditText)findViewById(R.id.edtNombreJugador);
        nameEquipo = (EditText)findViewById(R.id.edtNombreEquipo);
        camiseta = (ViewPager)findViewById(R.id.vp_Ropa);
        escudo = (ViewPager)findViewById(R.id.vp_Escudos);
        Button aceptar = (Button)findViewById(R.id.btnAceptar);
        Button cancelar = (Button)findViewById(R.id.btnCancelar);
        aceptar.setOnClickListener(new OnClickButton());
        cancelar.setOnClickListener(new OnClickButton());

        escudo.setAdapter(new Adapter(this,getEscudos()));
        camiseta.setAdapter(new Adapter(this, getCamisetas()));




    }

    private List<String> getEscudos(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            InputStream input = getAssets().open("escudos.json");
            String content = Utils.getStringFromInputStream(input);
            Log.e("content",content);
            JSONObject obj = new JSONObject(content);
            JSONArray arr = obj.getJSONArray("escudos");
            for(int i=0;i!=obj.getJSONArray("escudos").length();i++){
                String path = (String) arr.get(i);
                Log.e("path", path);
                list.add(path);;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<String> getCamisetas(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            InputStream input = getAssets().open("camisetas.json");
            String content = Utils.getStringFromInputStream(input);
            Log.e("content",content);
            JSONObject obj = new JSONObject(content);
            JSONArray arr = obj.getJSONArray("camisetas");
            for(int i=0;i!=obj.getJSONArray("camisetas").length();i++){
                String path = (String) arr.get(i);
                Log.e("path", path);
                list.add(path);;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }




    private class Adapter extends PagerAdapter{
        private List<String> list;
        private Context ctx;

        public Adapter (Context ctx, List<String> list){
            this.ctx = ctx ;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object arg1) {
            return view==arg1;
        } 
        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            ImageView view = new ImageView(ctx);
            Bitmap bitmap = Utils.getBitmapFromAssets(ctx,list.get(position));
            view.setImageBitmap(bitmap);
            ((ViewPager) collection).addView(view, 0);

            return view;
        }
        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            ((ViewPager) collection).removeView((ImageView) view);
        }

        @Override
        public float getPageWidth(final int position) {
            // this will have 3 pages in a single view
            return 0.32f;
        }

    }
    private class OnClickButton implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAceptar:
                    SharedPreferences preferencias = getSharedPreferences("firstEje", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putBoolean("first", false);
                    editor.commit();
                    startActivity(new Intent(CreateUser.this,MenuPrincipal.class));
                    break;
                case R.id.btnCancelar:
                    break;
            }
        }
    }
}