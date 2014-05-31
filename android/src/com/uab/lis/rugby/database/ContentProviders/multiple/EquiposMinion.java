package com.uab.lis.rugby.database.ContentProviders.multiple;

/**
 * Created by Manuel on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbLiga;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EquiposMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbEquipos.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        long i = db.insert(tbEquipos.TABLE,null,contentValues);
        for(String nom : new String[]{"Manu","Aitor","Victor","Victor M","Nicoleta","Suki","Aleix","Carles","Adria",
                "Esther","Aureli","Ruben","Richi","La Sombra","Ivan"}){
            ContentValues values = new ContentValues();
            values.put(tbJugadores.COL_NOMBRE,nom);
            long id = db.insertOrThrow(tbJugadores.TABLE,null,values);
            db.execSQL("INSERT INTO JUGADOR_EQUIPO VALUES("+id+","+i+");");
            db.execSQL("INSERT INTO JUGADOR_ROL VALUES('"+id+"',"+id+","+(Math.round(Math.random() * 2)+1)+");");
            for(int j : new int[]{1,2,3,4,5}) {
                db.execSQL("INSERT INTO JUGADOR_HABILIDAD VALUES(" + id + "," + j + ","
                        + Math.round(Math.random() * 100) + ");");
            }
        }

        //Creamos una liga para nuestro equipo
        String where = tbEquipos._ID + " <> " + i ;
        Cursor cursor = getContext().getContentResolver().query(UrisGenerated.getUriEquipos(0),null,where,null,null);
        while (cursor.moveToNext()){
            int idRibal = cursor.getInt(cursor.getColumnIndex(tbEquipos._ID));

            ContentValues values = new ContentValues();
            values.put(tbLiga.COL_ID_EQUIPO_1,i);
            values.put(tbLiga.COL_ID_EQUIPO_2,idRibal);
            values.put(tbLiga.COL_PUNTUACION_EQUIPO_1,-1);
            values.put(tbLiga.COL_PUNTUACION_EQUIPO_2,-1);
            values.put(tbLiga.COL_ID_LIGA,0);
            values.put(tbLiga.COL_FECHA,new SimpleDateFormat().format(new Date()));

            ContentValues values2 = new ContentValues();
            values2.put(tbLiga.COL_ID_EQUIPO_1,idRibal);
            values2.put(tbLiga.COL_ID_EQUIPO_2,i);
            values2.put(tbLiga.COL_PUNTUACION_EQUIPO_1,-1);
            values2.put(tbLiga.COL_PUNTUACION_EQUIPO_2,-1);
            values2.put(tbLiga.COL_ID_LIGA,0);
            values2.put(tbLiga.COL_FECHA,new SimpleDateFormat().format(new Date()));

            db.insertOrThrow(tbLiga.TABLE,null,values);
            db.insertOrThrow(tbLiga.TABLE,null,values2);
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