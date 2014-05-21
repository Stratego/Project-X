package com.uab.lis.rugby.database.ContentProviders.multiple;

/**
 * Created by Manuel on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class HabilidadesMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE + "/#/" + tbHabilidades.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String idUser = uri.getPathSegments().get(1);
        String idEquipo = uri.getPathSegments().get(3);
        String idJugador = uri.getPathSegments().get(5);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tbJugadores.TABLE + " as j, " + tbJugadorHabilidad.TABLE + " as jh, " + tbHabilidades.TABLE + " as h");
        String where = "j." + tbJugadores._ID + " = jh." + tbJugadorHabilidad.COL_JUGADOR +
                " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = h." + tbHabilidades._ID +
                " and j." + tbJugadores._ID + " = " + idJugador;
        String[] columnas = new String[]{"h."+tbHabilidades._ID,"h."+tbHabilidades.COL_NOMBRE,"h."+tbHabilidades.COL_DESCRIPCION,"jh."+tbJugadorHabilidad.COL_VALOR};
        return queryBuilder.query(db,columnas,where,null,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbHabilidades.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbHabilidades.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbHabilidades.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbHabilidades.TABLE;
    }
}