package com.uab.lis.rugby.database.ContentProviders;

/**
 * Created by Manuel on 20/04/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbJugadorPowerup;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class JugadorPowerupMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbJugadorPowerup.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String id = uri.getPathSegments().get(0);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbJugadores.TABLE+" JOIN "+tbJugadorPowerup.TABLE+" ON "+ tbJugadores._ID+"="+tbJugadorPowerup.COL_JUGADOR
        );
        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbJugadorPowerup.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbJugadorPowerup.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbJugadorPowerup.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbJugadorPowerup.TABLE;
    }
}