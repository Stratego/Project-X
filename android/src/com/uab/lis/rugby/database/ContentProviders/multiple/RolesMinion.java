package com.uab.lis.rugby.database.ContentProviders.multiple;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.*;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de los roles asociados a una habilidad de un jugador de un equipo de un usuario.
 */
public class RolesMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE + "/#/" + tbRoles.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String idUser = uri.getPathSegments().get(1);
        String idEquipo = uri.getPathSegments().get(3);
        String idJugador = uri.getPathSegments().get(5);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbJugadores.TABLE + " as j, " +
                tbJugadorRol.TABLE + " as jr, " +
                tbRoles.TABLE + " as r"
        );
        String where = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
                " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
                " and j." + tbJugadores._ID + " = " + idJugador;
        String[] columnas = new String[]{"r."+tbRoles._ID,"r."+tbRoles.COL_NOMBRE,"r."+tbRoles.COL_DESCRIPCION};
        return queryBuilder.query(db,columnas,where,null,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbRoles.TABLE,null,contentValues);
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