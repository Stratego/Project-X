package com.uab.lis.rugby.database.ContentProviders;

/**
 * Created by Manuel on 29/03/2014.
 */
import android.database.sqlite.SQLiteDatabase;
import com.uab.lis.rugby.database.libContentProvider.DespicableContentProvider;

public class MyAppContentProvider extends DespicableContentProvider {
    public static final String AUTHORITY = "com.example.PruebasManu";
    private static SQLiteDatabase db;

    @Override
    public void recruitMinions() {
        addMinion(new JugadoresMinionContentProvider());
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