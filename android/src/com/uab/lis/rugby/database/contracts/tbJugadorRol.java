package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 20/04/2014.
 */
public class tbJugadorRol implements BaseColumns {

    public static final String TABLE = "JUGADOR_ROL";

    public static final String COL_JUGADOR = "idJugador";

    public static final String COL_ROL = "idRol";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_JUGADOR + " INTEGER, "
                    + COL_ROL + " INTEGER )";
}