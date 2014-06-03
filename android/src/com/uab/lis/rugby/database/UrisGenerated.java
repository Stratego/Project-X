package com.uab.lis.rugby.database;

import android.net.Uri;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.contracts.*;

/**
 * Mediante esta clase se obtienen las URIs absolutas de las tablas y contenidos de la base de datos.
 */
public class UrisGenerated {

    /**
     * Obtiene La URI de la tabla de usuarios.
     *
     * @return La URI de la tabla de usuarios.
     */
    public static Uri getUriUsuario(){
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarios.TABLE);
    }

    /**
     * Obtiene la URI de un usuario.
     *
     * @param id El identificador del usuario.
     * @return La URI del usuario.
     */
    public static Uri getUriUsuario(int id){
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarios.TABLE + "/" + id);
    }

    /**
     * Obtiene la URI de la tabla de equipos de un usuario.
     *
     * @param idusuario El identificador del usuario.
     * @return La URI de la tabla de equipos del usuario.
     */
    public static Uri getUriEquipos(int idusuario) {
        Uri base = getUriUsuario(idusuario);
        return Uri.withAppendedPath(base, tbEquipos.TABLE);
    }

    /**
     * Obtiene la URI del equipo de un usuario.
     *
     * @param idusuario El identificador del usuario.
     * @param idequipo El identificador del equipo.
     * @return La URI del equipo del usuario
     */
    public static Uri getUriEquipo(int idusuario,int idequipo) {
        Uri base = getUriUsuario(idusuario);
        return Uri.withAppendedPath(base, tbEquipos.TABLE + "/" + idequipo);
    }

    /**
     * Obtiene la URI de la tabla que relaciona usuarios y equipos.
     *
     * @return La URI de la tabla que relaciona usuarios y equipos.
     */
    public static Uri getUriEquipoUsuario() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbUsuarioEquipo.TABLE);
    }

    /**
     * Obtiene la URI de la tabla de jugadores de un equipo de un usuario.
     *
     * @param iduser El identificador del usuario.
     * @param equipo El identificador del equipo.
     * @return La URI de la tabla de jugadores del equipo del usuario.
     */
    public static Uri getUriJugadoresEquipo(int iduser, int equipo){
        Uri base = getUriEquipo(iduser,equipo);
        base = Uri.withAppendedPath(base, tbJugadores.TABLE);
        return base;
    }

    /**
     * Obtiene la URI de un jugador de un equipo de un usuario.
     *
     * @param iduser El identificador del usuario.
     * @param equipo El identificador del equipo.
     * @param jugador El identificador del jugador.
     * @return La URI del jugador del equipo del usuario.
     */
    public static Uri getUriJugadoresEquipoItem(int iduser, int equipo, int jugador){
        Uri base = getUriJugadoresEquipo(iduser, equipo);
        base = Uri.withAppendedPath(base,jugador + "");
        return base;
    }

    /**
     * Obtiene la URI de la tabla de roles para el jugador de un equipo de un usuario.
     *
     * @param iduser El identificador del usuario.
     * @param equipo El identificador del equipo.
     * @param jugador El identificador del jugador.
     * @return La URI de la tabla de roles para el jugador del equipo del usuario.
     */
    public static Uri getUriRol(int iduser, int equipo, int jugador) {
        Uri base = getUriJugadoresEquipoItem(iduser, equipo, jugador);
        base = Uri.withAppendedPath(base, tbRoles.TABLE);
        return base;
    }

    /**
     * Obtiene la URI de la tabla de habilidades para el jugador de un equipo de un usuario.
     *
     * @param iduser El identificador del usuario.
     * @param equipo El identificador del equipo.
     * @param jugador El identificador del jugador.
     * @return La URI de la tabla de habilidades para el jugador del equipo del usuario.
     */
    public static Uri getUriHabilidades(int iduser, int equipo, int jugador) {
        Uri base = getUriJugadoresEquipoItem(iduser,equipo,jugador);
        base = Uri.withAppendedPath(base, tbHabilidades.TABLE);
        return base;
    }

    /**
     * Obtiene la URI de la tabla del historial de partidos.
     *
     * @return La URI de la tabla del historial de partidos.
     */
    public static Uri getUriHistorialPArtida() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbHistorialPartido.TABLE);
    }

    /**
     * Obtiene la URI de la tabla de jugadores.
     *
     * @return La URI de la tabla de jugadores.
     */
    public static Uri getUriAllJugadores() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbJugadores.TABLE);
    }

    /**
     * Obtiene la URI de la tabla que relaciona jugadores y habilidades.
     *
     * @return La URI de la tabla que relaciona jugadores y habilidades.
     */
    public static Uri getUriJugadorHabilidad() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbJugadorHabilidad.TABLE);
    }

    /**
     * Obtiene la URI de la tabla de objetos.
     *
     * @return La URI de la tabla de objetos.
     */
    public static Uri getUriAllObjetos() {
        Uri base = MyAppContentProvider.URI_BASE;
        return Uri.withAppendedPath(base, tbObjetos.TABLE);
    }
}
