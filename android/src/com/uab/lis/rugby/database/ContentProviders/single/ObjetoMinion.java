package com.uab.lis.rugby.database.ContentProviders.single;

/**
 * Created by Adria on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class ObjetoMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE + "/#/" + tbHabilidades.TABLE + "/#/" + tbObjetos.TABLE + "/#";
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbObjetos.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        throw new NoClassDefFoundError("no definido");
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