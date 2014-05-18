package com.uab.lis.rugby.ui.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.*;

/**
 * Created by Manuel on 13/05/14.
 */

public class TiendaObjetos extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    public static final String OBJETO = "objeto";
    public static final String DESCRIPCION = "descripcion";

    String selection_todos = null;
    String selection = selection_todos;


    /** Se ejecuta cuando se crea la actividad por primera vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendaobjetos);
        this.getListView().setDividerHeight(2);

        fillData();
        registerForContextMenu(getListView());
    }

    public void onButtonClicked(View view) {

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.tiendaobjetos_todos:
                selection = selection_todos;
                getLoaderManager().restartLoader(0, null, this);
                break;
        }
    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] { OBJETO, DESCRIPCION };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] { R.id.txtNombreObjeto, R.id.txtDescripcion };

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendaobjetos_filaobjeto, null, from,
                to, 0);

        setListAdapter(adapter);
    }

    // crea un nuevo loader despues de la llamada initLoader ()
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{ "o."+tbObjetos._ID + " as _id", "o."+tbObjetos.COL_NOMBRE +
                " as " + OBJETO, "o."+tbObjetos.COL_DESCRIPCION + " as " + DESCRIPCION };
        CursorLoader cursorLoader = new CursorLoader(this, UrisGenerated.getUriAllObjetos(), projection, selection, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // datos ya no disponibles, eliminar referencia
        adapter.swapCursor(null);
    }

}