package com.uab.lis.rugby.database.ContentProviders;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbJugadorObjeto;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de la tabla que relaciona jugadores y objetos.
 */
public class JugadorObjetoMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbJugadorObjeto.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String id = uri.getPathSegments().get(0);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbJugadores.TABLE+" JOIN "+tbJugadorObjeto.TABLE+" ON "+ tbJugadores._ID+"="+tbJugadorObjeto.COL_JUGADOR
        );
        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbJugadorObjeto.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbJugadorObjeto.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbJugadorObjeto.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbJugadorObjeto.TABLE;
    }
}