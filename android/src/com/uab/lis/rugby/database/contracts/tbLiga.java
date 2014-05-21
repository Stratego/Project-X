package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Adria on 20/05/2014.
 */
public class tbLiga implements BaseColumns {
    public static final String TABLE = "LIGA";

    public static final String COL_ID_LIGA = "identificadorliga";

    public static final String COL_ID_EQUIPO_1 = "idequipo1";

    public static final String COL_ID_EQUIPO_2 = "idequipo2";

    public static final String COL_PUNTUACION_EQUIPO_1 = "puntuacion1";

    public static final String COL_PUNTUACION_EQUIPO_2 = "puntuacion2";

    public static final String COL_FECHA = "fecha";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ID_EQUIPO_1 + " INTEGER, "
                    + COL_ID_EQUIPO_2 + " INTEGER, "
                    + COL_PUNTUACION_EQUIPO_1 + " INTEGER, "
                    + COL_PUNTUACION_EQUIPO_2 + " INTEGER, "
                    + COL_ID_LIGA + " INTEGER, "
                    + COL_FECHA + " TEXT)";
}
