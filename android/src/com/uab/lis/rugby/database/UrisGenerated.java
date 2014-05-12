package com.uab.lis.rugby.database;

import android.net.Uri;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.contracts.*;

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

    public static Uri getUriEquipos(int idusuario) {
        Uri base = getUriUsuario(idusuario);
        return Uri.withAppendedPath(base, tbEquipos.TABLE);
    }
    public static Uri getUriEquipo(int idusuario,int idequipo) {
        Uri base = getUriUsuario(idusuario);
        return Uri.withAppendedPath(base, tbEquipos.TABLE + "/" + idequipo);
    }


    public static Uri getUriEquipoUsuario() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarioEquipo.TABLE);
    }
    public static Uri getUriJugadoresEquipo(int iduser, int equipo){
        Uri base = getUriEquipo(iduser,equipo);
        base = Uri.withAppendedPath(base, tbJugadores.TABLE);
        return base;
    }
    public static Uri getUriJugadoresEquipoItem(int iduser, int equipo, int jugador){
        Uri base = getUriJugadoresEquipo(iduser, equipo);
        base = Uri.withAppendedPath(base,jugador + "");
        return base;
    }

    public static Uri getUriRol(int iduser, int equipo, int jugador) {
        Uri base = getUriJugadoresEquipoItem(iduser,equipo,jugador);
        base = Uri.withAppendedPath(base, tbRoles.TABLE);
        return base;
    }

    public static Uri getUriHabilidades(int iduser, int equipo, int jugador) {
        Uri base = getUriJugadoresEquipoItem(iduser,equipo,jugador);
        base = Uri.withAppendedPath(base, tbHabilidades.TABLE);
        return base;
    }
}
