package com.uab.lis.rugby.database.ContentProviders.multiple;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Created by Manuel on 14/05/14.
 */
public class AllJugadoresMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbJugadores.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbJugadores.TABLE + " as j, " +
                tbJugadorRol.TABLE + " as jr, " +
                tbRoles.TABLE + " as r, " +
                tbJugadorEquipo.TABLE + " as je, " +
                tbEquipos.TABLE + " as e, " +
                tbJugadorHabilidad.TABLE + " as jh, " +
                tbHabilidades.TABLE + " as h"
        );
        return queryBuilder.query(db,projection,selection,selectionArgs,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbJugadores.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbJugadores.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbJugadores.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbJugadores.TABLE;
    }
}