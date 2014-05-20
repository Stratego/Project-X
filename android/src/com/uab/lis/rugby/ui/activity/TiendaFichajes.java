package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.*;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.*;

/**
 * Created by Manuel on 13/05/14.
 */

public class TiendaFichajes extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    public static final String VERDE = "#669900";
    public static final String JUGADOR = "jugador";
    public static final String ROL = "rol";
    public static final String EQUIPO = "equipo";
    public static final String HABILIDAD = "habilidad";
    public static final String VALOR_HABILIDAD = "valor_habilidad";
    public static final String ATACANTE = "'atacante'";
    public static final String DEFENSA = "'defensa'";
    public static final String CHUTADOR = "'chutador'";
    public static final String SELECTION_TODOS =
            "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID;
    public static final String SELECTION_ATACANTE = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + ATACANTE;
    public static final String SELECTION_DEFENSA = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + DEFENSA;
    public static final String SELECTION_CHUTADOR = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + CHUTADOR;
    String selection = SELECTION_TODOS;
    public int item_id;

    String[] projection = new String[]{
            "j."+tbJugadores._ID + " as _id",
            "j."+tbJugadores.COL_NOMBRE + " as " + JUGADOR,
            "r."+tbRoles.COL_NOMBRE + " as " + ROL,
            "e."+tbEquipos.COL_NOMBRE + " as " + EQUIPO
    };

    // Identifies a particular Loader being used in this component
    private static final int JUGADORES_LOADER = 0;
    private static final int HABILIDADES_LOADER = 1;

    //Se ejecuta cuando se crea la actividad por primera vez
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendafichajes);

        ListView lvJugadores = (ListView) findViewById(R.id.listaJugadores);
        lvJugadores.setDividerHeight(2);
        fillData();
        registerForContextMenu(lvJugadores);
        lvJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                item_id = cursor.getInt(cursor.getColumnIndex("_id"));
                String item_nombre = cursor.getString(cursor.getColumnIndex(JUGADOR));
                Dialog dialog = new Dialog(TiendaFichajes.this);
                dialog.setContentView(R.layout.activity_tiendafichajes_popup);
                dialog.setTitle(item_nombre);
                TextView textView = (TextView)dialog.findViewById(R.id.textViewTiendaFichajesPopup);
                textView.setText("Aquí van las habilidades del jugador con ID=" + item_id);

                // Campos de la base de datos (projection)
                String[] from = new String[] {
                        HABILIDAD,
                        VALOR_HABILIDAD
                };
                // Campos de la interfaz a los que mapeamos
                int[] to = new int[] {
                        R.id.txtNombreHabilidad,
                        R.id.txtValorHabilidad
                };

                getLoaderManager().initLoader(HABILIDADES_LOADER, null, TiendaFichajes.this);
                adapter = new SimpleCursorAdapter(TiendaFichajes.this, R.layout.activity_tiendafichajes_filahabilidad, null, from, to, 0);

                ListView lvHabilidades = (ListView) findViewById(R.id.listaHabilidades);
                //ESTO PETA
                //lvHabilidades.setAdapter(adapter);

                dialog.show();

            }

        });

        final EditText campoNombre = (EditText)findViewById(R.id.tiendafichajes_edtNombre);
        campoNombre.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
                RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
                RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);

                //Ponemos verdes los botones de roles
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //Obtenemos el nombre introducido por el usuario
                String nombre = "'%"+campoNombre.getText().toString()+"%'";

                //Ponemos el where que selecciona los jugadores con el nombre introducido
                String selection_nombre = SELECTION_TODOS + " and j." + tbJugadores.COL_NOMBRE + " like " + nombre;
                selection = selection_nombre;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(0, null, TiendaFichajes.this);
            }
        });
    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] {
                JUGADOR,
                ROL,
                EQUIPO
        };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] {
                R.id.txtNombreJugador,
                R.id.txtNombreRol,
                R.id.txtNombreEquipo
        };

        getLoaderManager().initLoader(JUGADORES_LOADER, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendafichajes_filajugador, null, from, to, 0);

        ListView lvJugadores = (ListView) findViewById(R.id.listaJugadores);
        lvJugadores.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);

        // Comprobar que radio button ha sido clickado
        switch(view.getId()) {
            case R.id.atacante:

                //Ponemos rojo el boton 'Atacante' y verdes los demas
                botonAtacante.setBackgroundColor(Color.RED);
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //Ponemos el where que selecciona los jugadores con rol 'atacante'
                selection = SELECTION_ATACANTE;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, this);

                break;

            case R.id.defensa:

                //Ponemos rojo el boton 'Defensa' y verdes los demas
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.RED);
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //Ponemos el where que selecciona los jugadores con rol 'defensa'
                selection = SELECTION_DEFENSA;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, this);

                break;

            case R.id.chutador:

                //Ponemos rojo el boton 'Chutador' y verdes los demas
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.RED);

                //Ponemos el where que selecciona los jugadores con rol 'chutador'
                selection = SELECTION_CHUTADOR;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, this);

                break;
        }
    }

    public void onButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
        Button botonTodos=(Button)findViewById(R.id.todos);

        // Comprobar que ha sido clickado el boton 'Ver todos'
        switch(view.getId()) {
            case R.id.todos:

                //Ponemos verdes los botones de roles
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //Cambiamos la imagen del boton 'Ver todos' en funcion de su estado
                botonTodos.setBackgroundResource(R.drawable.selector_boton);

                //Ponemos el where que selecciona todos los jugadores
                selection = SELECTION_TODOS;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, this);

                break;
        }
    }

    // Crea un nuevo loader despues de la llamada initLoader ()
    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {

        /*
        * Takes action based on the ID of the Loader that's being created
        */
        switch (loaderID) {
            case JUGADORES_LOADER:
                projection = new String[]{
                        "j."+tbJugadores._ID + " as _id",
                        "j."+tbJugadores.COL_NOMBRE + " as " + JUGADOR,
                        "r."+tbRoles.COL_NOMBRE + " as " + ROL,
                        "e."+tbEquipos.COL_NOMBRE + " as " + EQUIPO
                };
                // Returns a new CursorLoader
                return new CursorLoader(
                    this,                               // Parent activity context
                    UrisGenerated.getUriAllJugadores(), // Table to query
                    projection,                         // Projection to return
                    selection,                               // No selection clause
                    null,                               // No selection arguments
                    null                                // Default sort order
                );

            case HABILIDADES_LOADER:
                projection = new String[]{
                        "jh."+tbJugadorHabilidad.COL_JUGADOR + " as _id",
                        "h."+tbHabilidades.COL_NOMBRE + " as " + HABILIDAD,
                        "jh."+tbJugadorHabilidad.COL_VALOR + " as " + VALOR_HABILIDAD
                };

                selection = "_id = " + item_id + " and h."+tbHabilidades._ID + "= jh." + tbJugadorHabilidad.COL_HABILIDAD;

                // Returns a new CursorLoader
                return new CursorLoader(
                        this,                                   // Parent activity context
                        UrisGenerated.getUriJugadorHabilidad(), // Table to query
                        projection,                             // Projection to return
                        selection,                              // No selection clause
                        null,                                   // No selection arguments
                        null                                    // Default sort order
                );

            default:
                // An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Datos ya no disponibles, eliminar referencia
        adapter.swapCursor(null);
    }

}