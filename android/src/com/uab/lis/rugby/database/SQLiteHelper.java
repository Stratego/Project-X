package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 24/03/14.
 */
import android.content.ContentResolver;
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
import com.uab.lis.rugby.database.models.Jugador;

import java.io.*;

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
            /*String uriBase = "com.uab.lis.rugby";

            ContentResolver cr = context.getContentResolver();
            Jugador jugador = new Jugador();
            jugador.setNombre("adria");
            cr.insert(Uri.parse(uriBase+"/"+tbEquipos.TABLE),Jugador.generateValues(jugador));
            */
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                backupDatabase();
            } catch (IOException e) {

                Log.e("aba",e.toString());
                Toast.makeText(context,"error save file --> "+e.toString(),Toast.LENGTH_LONG).show();
            }
        }

        //Coloca en la memoria SD una copia del archivo de la base de datos
        public void backupDatabase() throws IOException {
            //Open your local db as the input stream
            String inFileName = "/data/data/com.uab.lis.rugby/databases/"+nomBD;
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            String outFileName = Environment.getExternalStorageDirectory()
                    + "/"+nomBD;
            //Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);
            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer))>0){
                output.write(buffer, 0, length);
            }
            //Close the streams
            output.flush();
            output.close();
            fis.close();
        }
    }
}