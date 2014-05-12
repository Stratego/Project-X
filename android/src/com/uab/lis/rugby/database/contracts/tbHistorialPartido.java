package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Adria on 12/05/2014.
 */
public class tbHistorialPartido implements BaseColumns {

    public static final String TABLE = "HISTORIAL";

    public static final String COL_ID_EQUIPO_1 = "idequipo1";

    public static final String COL_ID_EQUIPO_2 = "idequipo2";

    public static final String COL_PUNTUACION_EQUIPO_1 = "puntuacion1";

    public static final String COL_PUNTUACION_EQUIPO_2 = "puntuacion2";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ID_EQUIPO_1 + " INTEGER, "
                    + COL_ID_EQUIPO_2 + " INTEGER, "
                    + COL_PUNTUACION_EQUIPO_1 + " INTEGER, "
                    + COL_PUNTUACION_EQUIPO_2 + " INTEGER)";
}