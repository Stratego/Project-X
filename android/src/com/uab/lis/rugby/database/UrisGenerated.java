package com.uab.lis.rugby.database;

import android.net.Uri;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbUsuarioEquipo;
import com.uab.lis.rugby.database.contracts.tbUsuarios;

/**
 * Created by adria on 3/05/14.
 */
public class UrisGenerated {
    public static Uri getUriUsuario(){
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarios.TABLE);
    }
    public static Uri getUriUsuario(int id){
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarios.TABLE + "/" + id);
    }

    public static Uri getUriEquipo() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbEquipos.TABLE);
    }
    public static Uri getUriEquipo(int id) {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbEquipos.TABLE + "/" + id);
    }


    public static Uri getUriEquipoUsuario() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarioEquipo.TABLE);
    }
    public static Uri getUriJugadoresEquipo(int equipo){
        Uri base = getUriEquipo();
        base = Uri.withAppendedPath(base,equipo + "");
        base = Uri.withAppendedPath(base, tbJugadores.TABLE);
        return base;
    }
    public static Uri getUriJugadoresEquipoItem(int equipo, int jugador){
        Uri base = getUriJugadoresEquipo(equipo);
        base = Uri.withAppendedPath(base,jugador + "");
        return base;
    }
}
