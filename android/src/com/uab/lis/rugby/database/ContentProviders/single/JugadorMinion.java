package com.uab.lis.rugby.database.ContentProviders.single;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de un jugador de un equipo de un usuario.
 */
public class JugadorMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE + "/#";
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String idUser = uri.getPathSegments().get(1);
        String idEquipo = uri.getPathSegments().get(3);
        String idJugador = uri.getPathSegments().get(5);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tbJugadores.TABLE + " as j");
        String where = "j." + tbJugadores._ID + " = " + idJugador;
        return queryBuilder.query(db,null,where,null,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        throw new NoClassDefFoundError("no definido");
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbJugadores.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        String idJugador = uri.getPathSegments().get(5);
        where = tbJugadores._ID + " = " + idJugador;
        return db.update(tbJugadores.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbJugadores.TABLE;
    }
}