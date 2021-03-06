package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona jugadores y habilidades.
 */
public class tbJugadorHabilidad implements BaseColumns {

    public static final String TABLE = "JUGADOR_HABILIDAD";

    public static final String COL_JUGADOR = "idJugador";

    public static final String COL_HABILIDAD = "idHabilidad";

    public static final String COL_VALOR = "valorHabilidad";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + COL_JUGADOR + " INTEGER, "
                    + COL_HABILIDAD + " INTEGER, "
                    + COL_VALOR + " INTEGER)";
}