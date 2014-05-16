package com.uab.lis.rugby.ui.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;

/**
 * Created by Manuel on 13/05/14.
 */

public class TiendaFichajes extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;
    private RadioGroup rgOpciones;
    String selection = null;
    String[] selectionArgs = null;
    public static final String JUGADOR = "jugador";
    public static final String ROL = "rol";
    public static final String EQUIPO = "equipo";


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
        String[] from = new String[] { JUGADOR, ROL, EQUIPO };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] { R.id.txtNombreJugador, R.id.txtNombreRol, R.id.txtNombreEquipo };

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendafichajes_filajugador, null, from,
                to, 0);

        setListAdapter(adapter);
    }

    // crea un nuevo loader despues de la llamada initLoader ()
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { JUGADOR, ROL, EQUIPO };
        CursorLoader cursorLoader = new CursorLoader(this, UrisGenerated.getUriAllJugadores(), projection, selection, selectionArgs, null);
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

    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.atacante:
                if (checked)
                    Toast.makeText(this, "Filtrando jugadores con rol de ATACANTE", Toast.LENGTH_SHORT).show();
                    selection = ROL + "=?";
                    selectionArgs = new String[] {"atacante"};
                    break;
            case R.id.defensa:
                if (checked)
                    Toast.makeText(this, "Filtrando jugadores con rol de DEFENSA", Toast.LENGTH_SHORT).show();
                    selection = ROL + "=?";
                    selectionArgs = new String[] {"defensa"};
                    break;
            case R.id.chutador:
                if (checked)
                    Toast.makeText(this, "Filtrando jugadores con rol de CHUTADOR", Toast.LENGTH_SHORT).show();
                    selection = ROL + "=?";
                    selectionArgs = new String[] {"chutador"};
                    break;
        }
        getLoaderManager().restartLoader(0, null, this);
    }

}