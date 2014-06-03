package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona jugadores y powerups.
 */
public class tbJugadorPowerup implements BaseColumns {

    public static final String TABLE = "JUGADOR_POWERUP";

    public static final String COL_JUGADOR = "idJugador";

    public static final String COL_OBJETO = "idObjeto";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_JUGADOR + " INTEGER, "
                    + COL_OBJETO + " INTEGER)";
}