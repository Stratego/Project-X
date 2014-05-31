package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 19/04/2014.
 */
public class tbObjetos implements BaseColumns{

    public static final String TABLE = "OBJETOS";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_DESCRIPCION = "descripcion";

    public static final String COL_TEXTURA = "textura";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT, "
                    + COL_DESCRIPCION + " TEXT, "
                    + COL_TEXTURA + " TEXT)";
}