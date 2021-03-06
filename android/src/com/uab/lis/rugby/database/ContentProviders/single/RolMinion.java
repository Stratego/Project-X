package com.uab.lis.rugby.database.ContentProviders.single;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbRoles;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de un rol de un jugador de un equipo de un usuario.
 */
public class RolMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE + "/#/" + tbRoles.TABLE + "/#";
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = uri.getPathSegments().get(0);
        return db.query(tbRoles.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        throw new NoClassDefFoundError("no definido");
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbRoles.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbRoles.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbRoles.TABLE;
    }
}