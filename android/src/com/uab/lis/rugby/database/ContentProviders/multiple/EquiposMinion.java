package com.uab.lis.rugby.database.ContentProviders.multiple;

/**
 * Created by Manuel on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class EquiposMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbEquipos.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        long i = db.insert(tbEquipos.TABLE,null,contentValues);
        for(String nom : new String[]{"Manu","Aitor","Victor","Victor M","Nicoleta","Suki","Aleix","Carles","Adria","Esther","Aureli","Ruben","Richi","La Sombra","Ivan"}){
            ContentValues values = new ContentValues();
            values.put(tbJugadores.COL_NOMBRE,nom);
            long id = db.insertOrThrow(tbJugadores.TABLE,null,values);
            db.execSQL("INSERT INTO JUGADOR_EQUIPO VALUES("+id+","+i+");");
        }

        return i;
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbEquipos.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbEquipos.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbEquipos.TABLE;
    }
}