package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona jugadores y extras.
 */
public class tbJugadorExtra implements BaseColumns {

    public static final String TABLE = "JUGADOR_EXTRAS";

    public static final String COL_JUGADOR = "idJugador";

    public static final String COL_EXTRAS = "idExtras";

    public static final String COL_VALOR = "valor";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_JUGADOR + " INTEGER, "
                    + COL_EXTRAS + " INTEGER, "
                    + COL_VALOR + " TEXT)";
}