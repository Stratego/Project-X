package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 28/04/14.
 */
public class tbPowerups implements BaseColumns {

    public static final String TABLE = "POWERUPS";

    public static final String COL_NOMBRE = "nombre";

    public static final String COL_DESCRIPCION = "descripcion";

    public static final String COL_HABILIDAD = "habilidad";

    public static final String COL_EFECTO = "efecto";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOMBRE + " TEXT, "
                    + COL_DESCRIPCION + " TEXT, "
                    + COL_HABILIDAD + " INTEGER, "
                    + COL_EFECTO + " INTEGER)";
}