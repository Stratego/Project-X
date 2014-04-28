package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 28/04/14.
 */
public class tbExtras implements BaseColumns {

    public static final String TABLE = "EXTRAS";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_DESCRIPCION = "descripcion";

    public static final String COL_VALOR = "valor";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT, "
                    + COL_DESCRIPCION + " TEXT, "
                    + COL_VALOR + " INTEGER)";
}