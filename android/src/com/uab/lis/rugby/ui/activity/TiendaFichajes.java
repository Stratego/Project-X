package com.uab.lis.rugby.ui.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.*;

/**
 * Created by Manuel on 13/05/14.
 */

public class TiendaFichajes extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    public static final String JUGADOR = "jugador";
    public static final String ROL = "rol";
    public static final String EQUIPO = "equipo";
    public static final String ATACANTE = "'atacante'";
    public static final String DEFENSA = "'defensa'";
    public static final String CHUTADOR = "'chutador'";
    public String nombre = null;

    String selection_todos = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID;
    String selection_atacante = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
            " and r." + tbRoles.COL_NOMBRE + " = " + ATACANTE;
    String selection_defensa = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
            " and r." + tbRoles.COL_NOMBRE + " = " + DEFENSA;
    String selection_chutador = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
            " and r." + tbRoles.COL_NOMBRE + " = " + CHUTADOR;
    String selection = selection_todos;


    /** Se ejecuta cuando se crea la actividad por primera vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendafichajes);

        final EditText campoNombre = (EditText)findViewById(R.id.tiendafichajes_edtNombre);
        campoNombre.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nombre = "'%"+campoNombre.getText().toString()+"%'";
                String selection_nombre = "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
                        " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
                        " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
                        " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
                        " and j." + tbJugadores.COL_NOMBRE + " like " + nombre;
                selection = selection_nombre;
                getLoaderManager().restartLoader(0, null, TiendaFichajes.this);
            }
        });

        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
    }

    public void onRadioButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.atacante:
                botonAtacante.setBackgroundColor(Color.RED);
                botonDefensa.setBackgroundColor(Color.parseColor("#669900"));
                botonChutador.setBackgroundColor(Color.parseColor("#669900"));
                selection = selection_atacante;
                getLoaderManager().restartLoader(0, null, this);
                break;
            case R.id.defensa:
                botonAtacante.setBackgroundColor(Color.parseColor("#669900"));
                botonDefensa.setBackgroundColor(Color.RED);
                botonChutador.setBackgroundColor(Color.parseColor("#669900"));
                selection = selection_defensa;
                getLoaderManager().restartLoader(0, null, this);
                break;
            case R.id.chutador:
                botonAtacante.setBackgroundColor(Color.parseColor("#669900"));
                botonDefensa.setBackgroundColor(Color.parseColor("#669900"));
                botonChutador.setBackgroundColor(Color.RED);
                selection = selection_chutador;
                getLoaderManager().restartLoader(0, null, this);
                break;
        }
    }

    public void onButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
        Button botonTodos=(Button)findViewById(R.id.todos);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.todos:
                botonAtacante.setBackgroundColor(Color.parseColor("#669900"));
                botonDefensa.setBackgroundColor(Color.parseColor("#669900"));
                botonChutador.setBackgroundColor(Color.parseColor("#669900"));
                botonTodos.setBackgroundResource(R.drawable.selector_boton);
                selection = selection_todos;
                getLoaderManager().restartLoader(0, null, this);
                break;
        }
    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] { JUGADOR, ROL, EQUIPO };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] { R.id.txtNombreJugador, R.id.txtNombreRol, R.id.txtNombreEquipo };

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendafichajes_filajugador, null, from, to, 0);

        setListAdapter(adapter);
    }

    // crea un nuevo loader despues de la llamada initLoader ()
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{ "j."+tbJugadores._ID + " as _id", "j."+tbJugadores.COL_NOMBRE +
                " as " + JUGADOR, "r."+tbRoles.COL_NOMBRE + " as " + ROL, "e."+tbEquipos.COL_NOMBRE + " as " + EQUIPO };
        CursorLoader cursorLoader = new CursorLoader(this, UrisGenerated.getUriAllJugadores(), projection, selection, null, null);
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