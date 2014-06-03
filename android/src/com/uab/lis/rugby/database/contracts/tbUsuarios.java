package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla de usuarios.
 */
public class tbUsuarios implements BaseColumns {

    public static final String TABLE = "USUARIOS";

    public static final String COL_NOMBRE = "nombre";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT)";
}