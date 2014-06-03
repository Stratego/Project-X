package com.uab.lis.rugby.database.ContentProviders;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbLiga;
import com.uab.lis.rugby.database.libContentProvider.MinionContentProvider;

/**
 * Gestiona el content provider de la tabla de ligas.
 */
public class LigaMinion extends MinionContentProvider {
    @Override
    public String getBasePath() {
        return tbLiga.TABLE;
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(
                tbEquipos.TABLE + " as e, " +
                tbEquipos.TABLE + " as e2, " +
                tbLiga.TABLE + " as l"
        );
        String[] columnas = new String[]{
                "l."+tbLiga._ID,
                "e."+tbEquipos.COL_ESCUDO,
                "e2."+tbEquipos.COL_ESCUDO + " AS "+tbEquipos.COL_ESCUDO+"2",
                "l."+tbLiga.COL_PUNTUACION_EQUIPO_1,
                "l."+tbLiga.COL_PUNTUACION_EQUIPO_2,
                "l."+tbLiga.COL_FECHA,
                "l."+tbLiga.COL_ID_LIGA,
                "l."+tbLiga.COL_ID_EQUIPO_1,
                "l."+tbLiga.COL_ID_EQUIPO_2
        };
        String where = "l."+tbLiga.COL_ID_EQUIPO_1 + " = e." +tbEquipos._ID
                + " and l."+tbLiga.COL_ID_EQUIPO_2 + " = e2."+tbEquipos._ID;

        Log.e("query Liga", queryBuilder.buildQuery(columnas, where, null, null, null, null));
        return queryBuilder.query(db,columnas,where,null,null,null,null);
    }

    @Override
    public long insert(SQLiteDatabase db, Uri uri, ContentValues contentValues) {
        return db.insert(tbLiga.TABLE,null,contentValues);
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String where, String[] selectionArgs) {
        return db.delete(tbLiga.TABLE,where,selectionArgs);
    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return db.update(tbLiga.TABLE,values,where,selectionArgs);
    }

    @Override
    public String getType() {
        return tbLiga.TABLE;
    }
}
