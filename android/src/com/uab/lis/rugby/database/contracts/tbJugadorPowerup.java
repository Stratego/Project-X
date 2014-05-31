package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 20/04/2014.
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