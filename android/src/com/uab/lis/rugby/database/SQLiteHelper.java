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
import android.util.Log;
import android.widget.Toast;
import com.uab.lis.rugby.database.ContentProviders.EquiposMinion;
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

        new DumyDatos().execute();

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

    class DumyDatos extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String uriBase = "content://com.uab.lis.uab.rugby";
            ContentResolver cr = context.getContentResolver();

            Jugador jugador1 = new Jugador();
            jugador1.setNombre("Manu");
            Uri uri_jugador1 = cr.insert(Uri.parse(uriBase+"/"+tbJugadores.TABLE),Jugador.generateValues(jugador1));

            Equipo equipo1;
            equipo1 = new Equipo();
            equipo1.setNombre("A-Team");
            Uri uri_equipo1 = cr.insert(Uri.parse(uriBase+"/"+tbEquipos.TABLE),Equipo.generateValues(equipo1));

            ContentValues cv = new ContentValues();
            cv.put(tbJugadorEquipo.COL_JUGADOR, ContentUris.parseId(uri_jugador1));
            cv.put(tbJugadorEquipo.COL_EQUIPO, ContentUris.parseId(uri_equipo1));
            cr.insert(Uri.parse(uriBase + "/" + tbJugadorEquipo.TABLE), cv);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
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