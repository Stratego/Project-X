package com.uab.lis.rugby.database.ContentProviders.multiple;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de la tabla de usuarios.
 */
public class UsuariosMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbUsuarios.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbUsuarios.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbUsuarios.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbUsuarios.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbUsuarios.TABLE;
    }
}