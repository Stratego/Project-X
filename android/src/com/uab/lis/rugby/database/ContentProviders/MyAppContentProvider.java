package com.uab.lis.rugby.database.ContentProviders;

/**
 * Created by Manuel on 29/03/2014.
 */

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.uab.lis.rugby.database.ContentProviders.multiple.*;
import com.uab.lis.rugby.database.ContentProviders.single.*;
import com.uab.lis.rugby.database.SQLiteHelper;
import com.uab.lis.rugby.database.libContentProvider.DespicableContentProvider;

public class MyAppContentProvider extends DespicableContentProvider {

    public static final String AUTHORITY = "com.uab.lis.rugby";
    public static final Uri URI_BASE = Uri.parse("content://"+AUTHORITY);
    private static SQLiteDatabase db;

    @Override
    public void recruitMinions() {
        addMinion(new UsuariosMinion());
        addMinion(new JugadoresMinion());
        addMinion(new AllJugadoresMinion());
        addMinion(new EquiposMinion());
        addMinion(new HabilidadesMinion());
        addMinion(new ObjetosMinion());
        addMinion(new AllObjetosMinion());
        addMinion(new RolesMinion());
        addMinion(new UsuarioEquipoMinion());
        addMinion(new JugadorEquipoMinion());
        addMinion(new JugadorRolMinion());
        addMinion(new JugadorObjetoMinion());
        addMinion(new JugadorHabilidadMinion());
        addMinion(new ExtrasMinion());
        addMinion(new JugadorExtraMinion());
        addMinion(new PowerupMinion());

        addMinion(new EquipoMinion());
        addMinion(new ExtraMinion());
        addMinion(new HabilidadMinion());
        addMinion(new JugadorMinion());
        addMinion(new ObjetoMinion());
        addMinion(new RolMinion());
        addMinion(new UsuarioMinion());

        addMinion(new LigaMinion());
        addMinion(new HistorialPartidosMinion());
    }

    @Override
    public  String getAuthority() {
        return AUTHORITY;
    }

    @Override
    public SQLiteDatabase getDb() {
        if (db == null) {
            db = SQLiteHelper.getInstance(getContext()).getReadableDatabase();
        }

        return db;
    }
}