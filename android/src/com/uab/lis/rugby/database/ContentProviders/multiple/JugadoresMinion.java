package com.uab.lis.rugby.database.ContentProviders.multiple;

/**
 * Created by Manuel on 29/03/2014.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadorEquipo;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbUsuarios;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

public class JugadoresMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbUsuarios.TABLE + "/#/" + tbEquipos.TABLE + "/#/" + tbJugadores.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        String idUser = uri.getPathSegments().get(1);
        String idEquipo = uri.getPathSegments().get(3);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbJugadores.TABLE + " as j, " +
                tbJugadorEquipo.TABLE + " as je, " +
                tbEquipos.TABLE + " as e"
        );
        String where = "j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
                " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
                " and e." + tbEquipos._ID + " = " + idEquipo;
        String[] columnas = new String[]{
                "j."+tbJugadores._ID,
                "j."+tbJugadores.COL_NOMBRE,
                "j."+tbJugadores.COL_POSX,
                "j."+tbJugadores.COL_POSY,
                "e."+tbEquipos.COL_EQUIPACION
        };
        return queryBuilder.query(db,columnas,where,null,null,null,null);
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