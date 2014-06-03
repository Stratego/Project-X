package com.uab.lis.rugby.database.ContentProviders.multiple;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de la tabla de objetos.
 */
public class AllObjetosMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbObjetos.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tbObjetos.TABLE + " as o");
        return queryBuilder.query(db,projection,selection,selectionArgs,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbObjetos.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbObjetos.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbObjetos.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbObjetos.TABLE;
    }
}