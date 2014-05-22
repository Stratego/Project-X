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
import android.text.Html;
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
    public static final String VALOR_FUERZA = "valor_fuerza";
    public static final String ATACANTE = "'atacante'";
    public static final String DEFENSA = "'defensa'";
    public static final String CHUTADOR = "'chutador'";
    public static final String SELECTION_TODOS =
            "j." + tbJugadores._ID + " = jr." + tbJugadorRol.COL_JUGADOR +
            " and jr." + tbJugadorRol.COL_ROL + " = r." + tbRoles._ID +
            " and j." + tbJugadores._ID + " = je." + tbJugadorEquipo.COL_JUGADOR +
            " and je." + tbJugadorEquipo.COL_EQUIPO + " = e." + tbEquipos._ID +
            " and j." + tbJugadores._ID + " = jh." + tbJugadorHabilidad.COL_JUGADOR +
            " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = h." + tbHabilidades._ID;
    public static final String SELECTION_ATACANTE = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + ATACANTE;
    public static final String SELECTION_DEFENSA = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + DEFENSA;
    public static final String SELECTION_CHUTADOR = SELECTION_TODOS + " and r." + tbRoles.COL_NOMBRE + " = " + CHUTADOR;
    String selection = SELECTION_TODOS;
    public int item_id;

    String[] projection = new String[]{
            "j."+tbJugadores._ID + " as _id",
            "j."+tbJugadores.COL_NOMBRE + " as " + JUGADOR,
            "r."+tbRoles.COL_NOMBRE + " as " + ROL,
            "e."+tbEquipos.COL_NOMBRE + " as " + EQUIPO,
            "h."+tbHabilidades.COL_NOMBRE + " as " + HABILIDAD,
            "jh."+tbJugadorHabilidad.COL_VALOR + " as " + VALOR_FUERZA
    };

    //Identifica el Loader particular que se esta utilizando
    private static final int JUGADORES_LOADER = 0;

    //Se ejecuta cuando se crea la actividad por primera vez
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendafichajes);

        final EditText campoNombre = (EditText)findViewById(R.id.tiendafichajes_edtNombre);

        TextView tvMayorIgual = (TextView) findViewById(R.id.txtMayorIgual);
        tvMayorIgual.setText(Html.fromHtml("&#8805;"));
        TextView tvMayorIgual2 = (TextView) findViewById(R.id.txtMayorIgual2);
        tvMayorIgual2.setText(Html.fromHtml("&#8805;"));
        TextView tvMayorIgual3 = (TextView) findViewById(R.id.txtMayorIgual3);
        tvMayorIgual3.setText(Html.fromHtml("&#8805;"));
        TextView tvMayorIgual4 = (TextView) findViewById(R.id.txtMayorIgual4);
        tvMayorIgual4.setText(Html.fromHtml("&#8805;"));

        SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
        TextView txtValorFuerzaSeekBar = (TextView)findViewById(R.id.txtValorFuerzaSeekBar);
        txtValorFuerzaSeekBar.setText(String.valueOf(sbFuerza.getProgress()));

        SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
        TextView txtValorDefensaSeekBar = (TextView)findViewById(R.id.txtValorDefensaSeekBar);
        txtValorDefensaSeekBar.setText(String.valueOf(sbDefensa.getProgress()));

        SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
        TextView txtValorResistenciaSeekBar = (TextView)findViewById(R.id.txtValorResistenciaSeekBar);
        txtValorResistenciaSeekBar.setText(String.valueOf(sbResistencia.getProgress()));

        SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
        TextView txtValorAtaqueSeekBar = (TextView)findViewById(R.id.txtValorAtaqueSeekBar);
        txtValorAtaqueSeekBar.setText(String.valueOf(sbAtaque.getProgress()));

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
                textView.setText("Has clickado sobre el jugador con ID=" + item_id);
                dialog.show();

            }

        });

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
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, TiendaFichajes.this);
            }
        });

        sbFuerza.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                // change progress text label with current seekbar value
                TextView txtValorFuerzaSeekBar = (TextView)findViewById(R.id.txtValorFuerzaSeekBar);
                txtValorFuerzaSeekBar.setText(String.valueOf(progressChanged));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                //Ponemos las demas barras de habilidades a 0
                SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
                SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
                SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
                sbDefensa.setProgress(0);
                sbResistencia.setProgress(0);
                sbAtaque.setProgress(0);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                //Ponemos verdes los botones de roles
                RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
                RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
                RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //hacemos un clear del campo 'Nombre'
                campoNombre.setText("");

                //Ponemos el where que selecciona los jugadores con la fuerza introducida
                String selection_fuerza = SELECTION_TODOS +
                        " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = 1" +
                        " and jh." + tbJugadorHabilidad.COL_VALOR + " >= " + progressChanged;
                selection = selection_fuerza;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, TiendaFichajes.this);
            }
        });
        sbDefensa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                // change progress text label with current seekbar value
                TextView txtValorDefensaSeekBar = (TextView)findViewById(R.id.txtValorDefensaSeekBar);
                txtValorDefensaSeekBar.setText(String.valueOf(progressChanged));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                //Ponemos las demas barras de habilidades a 0
                SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
                SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
                SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
                sbFuerza.setProgress(0);
                sbResistencia.setProgress(0);
                sbAtaque.setProgress(0);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                //Ponemos verdes los botones de roles
                RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
                RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
                RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //hacemos un clear del campo 'Nombre'
                campoNombre.setText("");

                //Ponemos el where que selecciona los jugadores con la defensa introducida
                String selection_defensa = SELECTION_TODOS +
                        " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = 2" +
                        " and jh." + tbJugadorHabilidad.COL_VALOR + " >= " + progressChanged;
                selection = selection_defensa;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, TiendaFichajes.this);
            }
        });
        sbResistencia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                // change progress text label with current seekbar value
                TextView txtValorResistenciaSeekBar = (TextView)findViewById(R.id.txtValorResistenciaSeekBar);
                txtValorResistenciaSeekBar.setText(String.valueOf(progressChanged));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                //Ponemos las demas barras de habilidades a 0
                SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
                SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
                SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
                sbFuerza.setProgress(0);
                sbDefensa.setProgress(0);
                sbAtaque.setProgress(0);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                //Ponemos verdes los botones de roles
                RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
                RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
                RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //hacemos un clear del campo 'Nombre'
                campoNombre.setText("");

                //Ponemos el where que selecciona los jugadores con la fuerza introducida
                String selection_resistencia = SELECTION_TODOS +
                        " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = 4" +
                        " and jh." + tbJugadorHabilidad.COL_VALOR + " >= " + progressChanged;
                selection = selection_resistencia;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, TiendaFichajes.this);
            }
        });
        sbAtaque.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                // change progress text label with current seekbar value
                TextView txtValorAtaqueSeekBar = (TextView)findViewById(R.id.txtValorAtaqueSeekBar);
                txtValorAtaqueSeekBar.setText(String.valueOf(progressChanged));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                //Ponemos las demas barras de habilidades a 0
                SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
                SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
                SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
                sbFuerza.setProgress(0);
                sbDefensa.setProgress(0);
                sbResistencia.setProgress(0);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                //Ponemos verdes los botones de roles
                RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
                RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
                RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
                botonAtacante.setBackgroundColor(Color.parseColor(VERDE));
                botonDefensa.setBackgroundColor(Color.parseColor(VERDE));
                botonChutador.setBackgroundColor(Color.parseColor(VERDE));

                //hacemos un clear del campo 'Nombre'
                campoNombre.setText("");

                //Ponemos el where que selecciona los jugadores con la fuerza introducida
                String selection_ataque = SELECTION_TODOS +
                        " and jh." + tbJugadorHabilidad.COL_HABILIDAD + " = 5" +
                        " and jh." + tbJugadorHabilidad.COL_VALOR + " >= " + progressChanged;
                selection = selection_ataque;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(JUGADORES_LOADER, null, TiendaFichajes.this);
            }
        });
    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] {
                JUGADOR,
                ROL,
                EQUIPO,
                VALOR_FUERZA
        };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] {
                R.id.txtNombreJugador,
                R.id.txtNombreRol,
                R.id.txtNombreEquipo,
                R.id.txtValorFuerza
        };

        getLoaderManager().initLoader(JUGADORES_LOADER, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendafichajes_filajugador, null, from, to, 0);
        ListView lvJugadores = (ListView) findViewById(R.id.listaJugadores);
        lvJugadores.setAdapter(adapter);
    }

    //Cuando se clicka alguno de los botones de 'Rol'
    public void onRadioButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
        EditText campoNombre = (EditText)findViewById(R.id.tiendafichajes_edtNombre);

        //Ponemos las barras de habilidades a 0
        SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
        SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
        SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
        SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
        sbFuerza.setProgress(0);
        sbDefensa.setProgress(0);
        sbResistencia.setProgress(0);
        sbAtaque.setProgress(0);

        //hacemos un clear del campo 'Nombre'
        campoNombre.setText("");

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

    //Cuando se clicka el boton 'Ver todos'
    public void onButtonClicked(View view) {

        RadioButton botonAtacante=(RadioButton)findViewById(R.id.atacante);
        RadioButton botonDefensa=(RadioButton)findViewById(R.id.defensa);
        RadioButton botonChutador=(RadioButton)findViewById(R.id.chutador);
        Button botonTodos=(Button)findViewById(R.id.todos);
        EditText campoNombre = (EditText)findViewById(R.id.tiendafichajes_edtNombre);

        //Ponemos las barras de habilidades a 0
        SeekBar sbFuerza=(SeekBar)findViewById(R.id.sbFuerza);
        SeekBar sbDefensa=(SeekBar)findViewById(R.id.sbDefensa);
        SeekBar sbResistencia=(SeekBar)findViewById(R.id.sbResistencia);
        SeekBar sbAtaque=(SeekBar)findViewById(R.id.sbAtaque);
        sbFuerza.setProgress(0);
        sbDefensa.setProgress(0);
        sbResistencia.setProgress(0);
        sbAtaque.setProgress(0);

        //hacemos un clear del campo 'Nombre'
        campoNombre.setText("");

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
                //Retorna un CursorLoader
                return new CursorLoader(
                    this,                               // Parent activity context
                    UrisGenerated.getUriAllJugadores(), // Table to query
                    projection,                         // Projection to return
                    selection,                               // No selection clause
                    null,                               // No selection arguments
                    null                                // Default sort order
                );

            default:
                //Se le ha pasado una id no valida
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