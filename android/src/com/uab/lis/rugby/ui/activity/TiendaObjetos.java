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

public class TiendaObjetos extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    public static final String NOMBRE_OBJETO = "objeto";
    public static final String DESCRIPCION = "descripcion";
    String selection = null;
    public int item_id;

    String[] projection = new String[]{
            "o."+tbObjetos._ID + " as _id",
            "o."+tbObjetos.COL_NOMBRE + " as " + NOMBRE_OBJETO,
            "o."+tbObjetos.COL_DESCRIPCION + " as " + DESCRIPCION
    };

    //Identifica el Loader particular que se esta utilizando
    private static final int OBJETOS_LOADER = 0;

    //Se ejecuta cuando se crea la actividad por primera vez
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendaobjetos);

        final EditText campoNombre = (EditText)findViewById(R.id.tiendaobjetos_edtNombre);

        ListView lvObjetos = (ListView) findViewById(R.id.listaObjetos);
        lvObjetos.setDividerHeight(2);
        fillData();
        registerForContextMenu(lvObjetos);
        lvObjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                item_id = cursor.getInt(cursor.getColumnIndex("_id"));
                String item_nombre = cursor.getString(cursor.getColumnIndex(NOMBRE_OBJETO));
                Dialog dialog = new Dialog(TiendaObjetos.this);
                dialog.setContentView(R.layout.activity_tienda_popup);
                dialog.setTitle(item_nombre);
                TextView textView = (TextView)dialog.findViewById(R.id.textViewTiendaPopup);
                textView.setText("Has clickado sobre el objeto con ID=" + item_id);
                //TODO adri: implementar funcionalidad del boton Comprar
                dialog.show();

            }

        });

        campoNombre.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Obtenemos el nombre introducido por el usuario
                String nombre = "'%"+campoNombre.getText().toString()+"%'";

                //Ponemos el where que selecciona los objetos con el nombre introducido
                String selection_nombre = "o." + tbObjetos.COL_NOMBRE + " like " + nombre;
                selection = selection_nombre;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(OBJETOS_LOADER, null, TiendaObjetos.this);
            }
        });

    }

    private void fillData() {

        // Campos de la base de datos (projection)
        String[] from = new String[] {
                NOMBRE_OBJETO,
                DESCRIPCION
        };
        // Campos de la interfaz a los que mapeamos
        int[] to = new int[] {
                R.id.txtNombreObjeto,
                R.id.txtDescripcion
        };

        getLoaderManager().initLoader(OBJETOS_LOADER, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_tiendaobjetos_filaobjeto, null, from, to, 0);
        ListView lvObjetos = (ListView) findViewById(R.id.listaObjetos);
        lvObjetos.setAdapter(adapter);
    }

    //Cuando se clicka el boton 'Ver todos'
    public void onButtonClicked(View view) {

        Button botonTodos=(Button)findViewById(R.id.todos);
        EditText campoNombre = (EditText)findViewById(R.id.tiendaobjetos_edtNombre);

        //hacemos un clear del campo 'Nombre'
        campoNombre.setText("");

        // Comprobar que ha sido clickado el boton 'Ver todos'
        switch(view.getId()) {
            case R.id.todos:

                //Cambiamos la imagen del boton 'Ver todos' en funcion de su estado
                botonTodos.setBackgroundResource(R.drawable.selector_boton);

                //Ponemos el where que selecciona todos los objetos
                selection = null;

                //Refrescamos el loader con la nueva consulta
                getLoaderManager().restartLoader(OBJETOS_LOADER, null, this);

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
            case OBJETOS_LOADER:
                //Retorna un CursorLoader
                return new CursorLoader(
                        this,
                        UrisGenerated.getUriAllObjetos(),
                        projection,
                        selection,
                        null,
                        null
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