package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 24/03/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbObjetos;

public class SQLiteHelper extends SQLiteOpenHelper {

    private SQLiteHelper(Context context){
        super(context,"DB_RugbySurvive",null,1);
    }

    public static SQLiteHelper getInstance(Context context){
        return new SQLiteHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbJugadores.CREATE_TABLE);
        db.execSQL(tbObjetos.CREATE_TABLE);
        db.execSQL(tbEquipos.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Jugadores");
        db.execSQL("DROP TABLE IF EXISTS Objetos");
        db.execSQL("DROP TABLE IF EXISTS Equipos");
        onCreate(db);
    }
}