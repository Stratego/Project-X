package com.uab.lis.rugby.ui.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import com.uab.lis.rugby.database.contracts.tbRoles;

/**
 * Created by Manuel on 13/05/14.
 */

public class TiendaFichajes extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;

    /** Se ejecuta cuando se crea la actividad por primera vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendafichajes);
        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] {"j."+tbJugadores.COL_NOMBRE, "r."+tbRoles.COL_NOMBRE, "e."+tbEquipos.COL_NOMBRE};
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] { R.id.txtNombreJugador, R.id.txtNombreRol, R.id.txtNombreEquipo};

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendafichajes_filajugador, null, from,
                to, 0);

        setListAdapter(adapter);
    }

    // crea un nuevo loader despues de la llamada initLoader ()
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { "j."+tbJugadores.COL_NOMBRE, "r."+tbRoles.COL_NOMBRE, "e."+tbEquipos.COL_NOMBRE };
        CursorLoader cursorLoader = new CursorLoader(this, UrisGenerated.getUriAllJugadores(), null, null, null, null);
        System.out.println(cursorLoader.toString());
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