package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 24/03/14.
 */
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.models.Equipo;
import com.uab.lis.rugby.database.models.Jugador;

import java.io.*;
import java.nio.channels.FileChannel;

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context context;
    private static String nomBD = "DB_RugbySurvive";
    private static int versionBD = 3;
    private SQLiteHelper(Context context){
        super(context,nomBD,null,versionBD);
        this.context = context;
    }

    public static SQLiteHelper getInstance(Context context){
        return new SQLiteHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbUsuarios.CREATE_TABLE);
        db.execSQL(tbJugadores.CREATE_TABLE);
        db.execSQL(tbObjetos.CREATE_TABLE);
        db.execSQL(tbEquipos.CREATE_TABLE);
        db.execSQL(tbHabilidades.CREATE_TABLE);
        db.execSQL(tbRoles.CREATE_TABLE);
        db.execSQL(tbUsuarioEquipo.CREATE_TABLE);
        db.execSQL(tbJugadorEquipo.CREATE_TABLE);
        db.execSQL(tbJugadorRol.CREATE_TABLE);
        db.execSQL(tbJugadorHabilidad.CREATE_TABLE);
        db.execSQL(tbJugadorObjeto.CREATE_TABLE);
        db.execSQL(tbExtras.CREATE_TABLE);
        db.execSQL(tbJugadorExtra.CREATE_TABLE);
        db.execSQL(tbPowerups.CREATE_TABLE);

        new DumyDatos().execute(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        String[] tablas = new String[]{
            tbJugadores.TABLE,
            tbObjetos.TABLE,
            tbEquipos.TABLE,
            tbHabilidades.TABLE,
            tbUsuarios.TABLE,
            tbRoles.TABLE,
            tbUsuarioEquipo.TABLE,
            tbJugadorEquipo.TABLE,
            tbJugadorRol.TABLE,
            tbJugadorObjeto.TABLE,
            tbJugadorHabilidad.TABLE,
            tbExtras.TABLE,
            tbJugadorExtra.TABLE,
            tbPowerups.TABLE,
        };
        for(String tab : tablas){
            db.execSQL("DROP TABLE IF EXISTS "+tab);
        }
        onCreate(db);
    }

    class DumyDatos extends AsyncTask<SQLiteDatabase,Void,Void>{

        @Override
        protected Void doInBackground(SQLiteDatabase... databases) {
            SQLiteDatabase db = databases[0];

            db.execSQL("INSERT INTO USUARIOS VALUES(1,'ANDROID');");
            db.execSQL("INSERT INTO EQUIPOS VALUES(1,'Equipo 1','Logo1.png','Jugador3E1.png');");
            db.execSQL("INSERT INTO EQUIPOS VALUES(2,'Equipo 2','Logo2.png','Jugador3E2.png');");
            db.execSQL("INSERT INTO EQUIPOS VALUES(3,'Equipo 3','Logo3.png','Jugador3E3.png');");
            db.execSQL("INSERT INTO EQUIPOS VALUES(4,'Equipo 4','Logo4.png','Jugador3E4.png');");
            db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(1,1,1);");
            db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(2,1,4);");
            db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(3,1,2);");
            db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(4,1,3);");

            int count = 1;
            for(int i : new int[]{1,2,3,4}) {
                for(String nom : new String[]{"Manu","Aitor","Victor","Victor M","Nicoleta","Suki","Aleix","Carles","Adria","Esther","Aureli","Ruben","Richi","La Sombra","Ivan"}){
                    db.execSQL("INSERT INTO JUGADORES VALUES("+(count)+",'"+nom+"',NULL,NULL);");
                    db.execSQL("INSERT INTO JUGADOR_EQUIPO VALUES("+count+","+count+","+i+");");
                    count++;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                Thread.sleep(1000 * 60 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            backupDatabase();
        }

        //Coloca en la memoria SD una copia del archivo de la base de datos
        public void backupDatabase() {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            FileChannel source=null;
            FileChannel destination=null;
            String currentDBPath = "/data/"+ "com.uab.lis.rugby" +"/databases/"+nomBD;
            String backupDBPath = nomBD;
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd, backupDBPath);
            try {
                source = new FileInputStream(currentDB).getChannel();
                destination = new FileOutputStream(backupDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}