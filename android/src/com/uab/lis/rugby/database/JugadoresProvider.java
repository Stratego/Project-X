package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 25/03/2014.
 */
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class JugadoresProvider extends ContentProvider {

    //Definición del CONTENT_URI
    private static final String uri =
            "content://com.example.PruebasManu/jugadores";

    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher
    private static final int JUGADORES = 1;
    private static final int JUGADORES_ID = 2;
    private static final UriMatcher uriMatcher;

    //Base de datos
    private SQLiteHelper jugdbh;
    private SQLiteDatabase db;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.PruebasManu", "jugadores", JUGADORES);
        uriMatcher.addURI("com.example.PruebasManu", "jugadores/#", JUGADORES_ID);
    }

    @Override
    public boolean onCreate() {
        jugdbh = SQLiteHelper.getInstance(getContext());
        db = jugdbh.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == JUGADORES_ID){
            where = Jugador._ID + uri.getLastPathSegment();
        }

        Cursor c = db.query(Jugador.TABLE, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long regId = 1;

        regId = db.insert(Jugador.TABLE, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == JUGADORES_ID){
            where = Jugador._ID + uri.getLastPathSegment();
        }

        cont = db.update(Jugador.TABLE, values, where, selectionArgs);

        return cont;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == JUGADORES_ID){
            where = Jugador._ID + uri.getLastPathSegment();
        }

        cont = db.delete(Jugador.TABLE, where, selectionArgs);

        return cont;
    }

    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match)
        {
            case JUGADORES:
                return "vnd.android.cursor.dir/vnd.example.jugador";
            case JUGADORES_ID:
                return "vnd.android.cursor.item/vnd.example.jugador";
            default:
                return null;
        }
    }
}