package com.uab.lis.rugby.database.ContentProviders;

/**
 * Created by Manuel on 29/03/2014.
 */

import android.database.sqlite.SQLiteDatabase;
import com.uab.lis.rugby.database.SQLiteHelper;
import com.uab.lis.rugby.database.libContentProvider.DespicableContentProvider;

public class MyAppContentProvider extends DespicableContentProvider {
    public static final String AUTHORITY = "com.uab.lis.rugby";
    private static SQLiteDatabase db;

    @Override
    public void recruitMinions() {
        addMinion(new UsuariosMinionContentProvider());
        addMinion(new JugadoresMinionContentProvider());
        addMinion(new EquiposMinionContentProvider());
        addMinion(new HabilidadesMinionContentProvider());
        addMinion(new ObjetosMinionContentProvider());
        addMinion(new RolesMinionContentProvider());
        addMinion(new UsuarioEquipoMinionContentProvider());
        addMinion(new JugadorEquipoMinionContentProvider());
        addMinion(new JugadorRolMinionContentProvider());
        addMinion(new JugadorObjetoMinionContentProvider());
        addMinion(new JugadorHabilidadMinionContentProvider());
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