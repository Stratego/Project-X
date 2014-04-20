package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 24/03/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.uab.lis.rugby.database.contracts.*;

public class SQLiteHelper extends SQLiteOpenHelper {

    private SQLiteHelper(Context context){
        super(context,"DB_RugbySurvive",null,1);
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS JUGADORES");
        db.execSQL("DROP TABLE IF EXISTS OBJETOS");
        db.execSQL("DROP TABLE IF EXISTS EQUIPOS");
        db.execSQL("DROP TABLE IF EXISTS HABILIDADES");
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        db.execSQL("DROP TABLE IF EXISTS ROLES");
        db.execSQL("DROP TABLE IF EXISTS USUARIO_EQUIPO");
        db.execSQL("DROP TABLE IF EXISTS JUGADOR_EQUIPO");
        db.execSQL("DROP TABLE IF EXISTS JUGADOR_ROL");
        db.execSQL("DROP TABLE IF EXISTS JUGADOR_OBJETO");
        db.execSQL("DROP TABLE IF EXISTS JUGADOR_HABILIDAD");
        onCreate(db);
    }
}