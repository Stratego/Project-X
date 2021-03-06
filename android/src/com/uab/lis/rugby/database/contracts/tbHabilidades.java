package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla de habilidades.
 */
public class tbHabilidades implements BaseColumns {

    public static final String TABLE = "HABILIDADES";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_DESCRIPCION = "descripcion";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT, "
                    + COL_DESCRIPCION + " TEXT);";
}