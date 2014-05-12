package com.uab.lis.rugby.ui.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
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
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.Utilis.EquipoCursor;
import com.uab.lis.rugby.database.Utilis.UsuarioCursor;
import com.uab.lis.rugby.database.contracts.tbUsuarioEquipo;
import com.models.Equipo;
import com.models.Usuario;
import com.uab.lis.rugby.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adria on 30/03/14.
 */
public class CreateUser extends BaseActivity {
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

    private List<String[]> getEscudos(){
        ArrayList<String[]> list = new ArrayList<String[]>();
        try {
            InputStream input = getAssets().open("escudos.json");
            String content = Utils.getStringFromInputStream(input);
            Log.e("content",content);
            JSONObject obj = new JSONObject(content);
            JSONArray arr = obj.getJSONArray("escudos");
            for(int i=0;i!=obj.getJSONArray("escudos").length();i++){
                JSONObject element = (JSONObject) arr.get(i);
                String normal = (String)element.get("normal");
                String focus = (String)element.get("focus");

                list.add(new String[]{normal,focus});;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<String[]> getCamisetas(){
        ArrayList<String[]> list = new ArrayList<String[]>();
        try {
            InputStream input = getAssets().open("camisetas.json");
            String content = Utils.getStringFromInputStream(input);
            Log.e("content",content);
            JSONObject obj = new JSONObject(content);
            JSONArray arr = obj.getJSONArray("camisetas");
            for(int i=0;i!=obj.getJSONArray("camisetas").length();i++){
                JSONObject element = (JSONObject) arr.get(i);
                String normal = (String)element.get("normal");
                String focus = (String)element.get("focus");

                list.add(new String[]{normal,focus});;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }




    private class Adapter extends PagerAdapter{
        private List<String[]> list;
        private Context ctx;
        private int positionSelect = -1;
        private HashMap<Integer,View> elements;

        public Adapter (Context ctx, List<String[]> list){
            this.ctx = ctx ;
            this.list = list;
            elements = new HashMap<Integer, View>();
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
        public Object instantiateItem(ViewGroup collection, final int position) {
            try{
                ImageView view = new ImageView(ctx);

                Log.e("focus",list.get(position)[1]);
                Log.e("normal",list.get(position)[0]);

                Drawable pressed = Utils.getDrawableFromAssets(ctx,list.get(position)[1]);
                Drawable normal = Utils.getDrawableFromAssets(ctx,list.get(position)[0]);

                StateListDrawable states = new StateListDrawable();
                states.addState(new int[] {android.R.attr.state_pressed},pressed);
                states.addState(new int[] {android.R.attr.state_focused},pressed);
                states.addState(new int[] {android.R.attr.state_selected},pressed);
                states.addState(new int[]{}, normal);

                view.setImageDrawable(states);
                //view.setImageResource(R.drawable.selector_imagen);
                view.setOnClickListener(new ClickItem(this,position));
                ((ViewPager) collection).addView(view, position);
                elements.put(position,view);


                return view;
            }catch(IOException ex){

            }
            return null;
        }
        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            ((ViewPager) collection).removeView((ImageView) view);
        }

        @Override
        public float getPageWidth(final int position) {
            return 0.32f;
        }

        private void setSelectPosition(int position){
            this.positionSelect = position;

            for(int i = 0; i != elements.size();i++){
                View view = elements.get(i);
                if(positionSelect == i){
                    view.setSelected(true);
                }else{
                    view.setSelected(false);
                }
                view.invalidate();
            }

        }
        public int getPositionSelect(){
            return this.positionSelect;
        }

    }

    private class ClickItem implements View.OnClickListener{
        private int position;
        private Adapter padre;
        public ClickItem(Adapter padre,int position){
            this.position = position;
            this.padre = padre;
        }

        @Override
        public void onClick(View v) {
            padre.setSelectPosition(position);
            v.invalidate();

        }
    }

    private class OnClickButton implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAceptar:

                    String nameE = nameEquipo.getText().toString();
                    String nameU = nameUser.getText().toString();
                    int selectedC = ((Adapter)camiseta.getAdapter()).getPositionSelect();
                    int selectedE = ((Adapter)escudo.getAdapter()).getPositionSelect();

                    //creamos el usuario y lo insertamos
                    Usuario user = new Usuario();
                    user.setNombre(nameU);
                    Uri userUri = UrisGenerated.getUriUsuario();
                    Log.e("insert", userUri.toString());
                    Uri uriUserItem = getContentResolver().insert(userUri, UsuarioCursor.generateValues(user));
                    Log.e("insert", uriUserItem.toString());
                    int idU = Integer.parseInt(uriUserItem.getPathSegments().get(1));

                    //creamos el equipo y lo insertamos
                    Equipo equipo = new Equipo();
                    equipo.setNombre(nameE);
                    equipo.setEquipacion(getCamisetas().get(selectedC)[0]);
                    equipo.setEscudo(getEscudos().get(selectedE)[0]);
                    Uri equipoUri = UrisGenerated.getUriEquipos(idU);
                    Log.e("insert", equipoUri.toString());
                    Uri uriEquipoItem = getContentResolver().insert(equipoUri, EquipoCursor.generateValues(equipo));
                    Log.e("insert", uriEquipoItem.toString());

                    //unimos el equipo con el usuario
                    int idE = Integer.parseInt(uriEquipoItem.getPathSegments().get(3));

                    Uri equipoUsuarioUri = UrisGenerated.getUriEquipoUsuario();
                    ContentValues cv = new ContentValues();
                    cv.put(tbUsuarioEquipo.COL_EQUIPO,idE);
                    cv.put(tbUsuarioEquipo.COL_USUARIO,idU);
                    Uri uriEquipoUsuarioItem = getContentResolver().insert(equipoUsuarioUri,cv);

                    //marcamos que ya hemos generado el primer de todo
                    SharedPreferences preferencias = getSharedPreferences("firstEje", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putBoolean("first", false);
                    editor.putInt("usuarioID",idU);
                    editor.putInt("equipoID",idE);
                    editor.commit();
                    startActivity(new Intent(CreateUser.this,MenuPrincipal.class));
                    break;
                case R.id.btnCancelar:
                    break;
            }
        }
    }
}