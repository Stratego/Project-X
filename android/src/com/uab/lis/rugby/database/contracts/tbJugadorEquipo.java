package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona jugadores y equipos.
 */
public class tbJugadorEquipo implements BaseColumns {

    public static final String TABLE = "JUGADOR_EQUIPO";

    public static final String COL_JUGADOR = "idJugador";

    public static final String COL_EQUIPO = "idEquipo";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + COL_JUGADOR + " INTEGER, "
                    + COL_EQUIPO + " INTEGER)";
}