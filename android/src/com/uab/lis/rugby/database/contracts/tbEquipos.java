package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by adria on 27/03/14.
 */
public class tbEquipos implements BaseColumns{

    public static final String TABLE = "Equipos";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_ESCUDO = "escudo";

    public static final String COL_EQUIPACION = "equipacion";

    public static final String COL_ALINEACION = "alineacion";

    public static final String COL_JUGADORES = "jugadores";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT, "
                    + COL_ESCUDO + " TEXT, "
                    + COL_EQUIPACION + " TEXT, "
                    + COL_ALINEACION + " TEXT, "
                    + COL_JUGADORES + " TEXT)";
}