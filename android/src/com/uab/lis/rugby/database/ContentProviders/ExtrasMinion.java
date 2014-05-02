package com.uab.lis.rugby.database.ContentProviders;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbExtras;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Created by Manuel on 28/04/14.
 */
public class ExtrasMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {

        return tbExtras.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbExtras.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbExtras.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbExtras.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbExtras.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbExtras.TABLE;
    }
}