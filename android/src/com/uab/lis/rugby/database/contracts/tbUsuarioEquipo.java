package com.uab.lis.rugby.database.contracts;

import android.provider.BaseColumns;

/**
 * Define la tabla que relaciona usuarios y equipos.
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