package com.uab.lis.rugby.database.ContentProviders;

/**
 * Created by Manuel on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbHabilidades;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class HabilidadesMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbHabilidades.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbHabilidades.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
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