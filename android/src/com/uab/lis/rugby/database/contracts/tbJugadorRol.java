package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona jugadores y roles.
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