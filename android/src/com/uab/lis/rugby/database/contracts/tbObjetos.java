package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by adria on 27/03/14.
 */
public class tbObjetos implements BaseColumns{

    public static final String TABLE = "Objetos";

    public static final String COL_NOMBRE = "nombre";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT)";
}