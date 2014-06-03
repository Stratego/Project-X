package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla de jugadores.
 */
public class tbJugadores implements BaseColumns{

    public static final String TABLE = "JUGADORES";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_POSX = "posx";

    public static final String COL_POSY = "posy";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE + " TEXT, "
            + COL_POSX + " INTEGER, "
            + COL_POSY + " INTEGER)";
}

