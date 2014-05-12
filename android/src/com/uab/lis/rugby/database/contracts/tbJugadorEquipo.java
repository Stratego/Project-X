package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 20/04/2014.
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