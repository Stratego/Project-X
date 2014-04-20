package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 20/04/2014.
 */
public class tbUsuarioEquipo implements BaseColumns {

    public static final String TABLE = "USUARIO_EQUIPO";

    public static final String COL_USUARIO = "idUsuario";

    public static final String COL_EQUIPO = "idEquipo";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_USUARIO + " INTEGER, "
                    + COL_EQUIPO + " INTEGER)";
}