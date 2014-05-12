package com.uab.lis.rugby.database.Utilis;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.models.Habilidad;
import com.models.Jugador;
import com.models.Rol;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.tbHabilidades;
import com.uab.lis.rugby.database.contracts.tbJugadorHabilidad;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbRoles;

import java.util.ArrayList;

/**
 * Created by adria on 12/05/14.
 */
public class JugadorCursor {
    public static Jugador newInstance(Context context, Cursor cursor){
        int colId = cursor.getColumnIndex(tbJugadores._ID);
        int colNombre = cursor.getColumnIndex(tbJugadores.COL_NOMBRE);
        int x = cursor.getColumnIndex(tbJugadores.COL_POSX);
        int y = cursor.getColumnIndex(tbJugadores.COL_POSY);

        Jugador jugador = new Jugador();
        jugador.setId(cursor.getInt(colId));
        jugador.setNombre(cursor.getString(colNombre));
        jugador.setPosX(cursor.getInt(x));
        jugador.setPosY(cursor.getInt(y));

        Uri uriRol = UrisGenerated.getUriRol(0,0,jugador.getId());
        ContentResolver cr = context.getContentResolver();
        Cursor cursorRol = cr.query(uriRol,null,null,null,null);
        cursorRol.moveToFirst();
        if(cursorRol.getCount() > 0){
            do{
                Rol rol = new Rol();
                String nombre = cursorRol.getString(cursorRol.getColumnIndex(tbRoles.COL_NOMBRE));
                String descri = cursorRol.getString(cursorRol.getColumnIndex(tbRoles.COL_DESCRIPCION));
                int idR = cursorRol.getInt(cursorRol.getColumnIndex(tbRoles._ID));
                rol.setId(idR);
                rol.setNombre(nombre);
                rol.setDescripcion(descri);
                jugador.setRol(rol);
            }while (cursorRol.moveToNext());
        }

        Uri uriHabilidad = UrisGenerated.getUriHabilidades(0, 0, jugador.getId());
        cr = context.getContentResolver();
        Cursor cursorHabilidad = cr.query(uriHabilidad,null,null,null,null);
        cursorHabilidad.moveToFirst();
        ArrayList<Habilidad> habilidades = new ArrayList<Habilidad>();
        if(cursorHabilidad.getCount() > 0){
            do{
                Habilidad habil = new Habilidad();
                String nombre = cursorHabilidad.getString(cursorHabilidad.getColumnIndex(tbHabilidades.COL_NOMBRE));
                String descri = cursorHabilidad.getString(cursorHabilidad.getColumnIndex(tbHabilidades.COL_DESCRIPCION));
                int idR = cursorHabilidad.getInt(cursorHabilidad.getColumnIndex(tbHabilidades._ID));
                int valor = cursorHabilidad.getInt(cursorHabilidad.getColumnIndex(tbJugadorHabilidad.COL_VALOR));
                habil.setId(idR);
                habil.setNombre(nombre);
                habil.setDescripcion(descri);
                habil.setValor(valor);
                habilidades.add(habil);
            }while (cursorHabilidad.moveToNext());
        }

        jugador.setHabilidades(habilidades);

        return jugador;
    }
    public static ContentValues generateValues(Jugador jugador){
        ContentValues values = new ContentValues();
        values.put(tbJugadores._ID, jugador.getId());
        values.put(tbJugadores.COL_NOMBRE,jugador.getNombre());
        // FALTA COMPLETAR
        return values;
    }
}
