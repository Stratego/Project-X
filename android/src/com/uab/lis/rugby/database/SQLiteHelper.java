package com.uab.lis.rugby.database;

/**
 * Created by Manuel on 24/03/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import com.uab.lis.rugby.database.contracts.*;

import java.io.*;
import java.nio.channels.FileChannel;

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context context;
    private static String nomBD = "DB_RugbySurvive";
    private static int versionBD = 4;
    private SQLiteHelper(Context context){
        super(context,nomBD,null,versionBD);
        this.context = context;
    }

    public static SQLiteHelper getInstance(Context context){
        return new SQLiteHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbUsuarios.CREATE_TABLE);
        db.execSQL(tbJugadores.CREATE_TABLE);
        db.execSQL(tbObjetos.CREATE_TABLE);
        db.execSQL(tbEquipos.CREATE_TABLE);
        db.execSQL(tbHabilidades.CREATE_TABLE);
        db.execSQL(tbRoles.CREATE_TABLE);
        db.execSQL(tbUsuarioEquipo.CREATE_TABLE);
        db.execSQL(tbJugadorEquipo.CREATE_TABLE);
        db.execSQL(tbJugadorRol.CREATE_TABLE);
        db.execSQL(tbJugadorHabilidad.CREATE_TABLE);
        db.execSQL(tbJugadorObjeto.CREATE_TABLE);
        db.execSQL(tbExtras.CREATE_TABLE);
        db.execSQL(tbJugadorExtra.CREATE_TABLE);
        db.execSQL(tbPowerups.CREATE_TABLE);
        db.execSQL(tbHistorialPartido.CREATE_TABLE);
        db.execSQL(tbLiga.CREATE_TABLE);

        db.execSQL("INSERT INTO USUARIOS VALUES(1,'ANDROID');");
        db.execSQL("INSERT INTO EQUIPOS VALUES(1,'Equipo 1','logo1.png','Jugador3E1.png');");
        db.execSQL("INSERT INTO EQUIPOS VALUES(2,'Equipo 2','Logo2.png','Jugador3E2.png');");
        db.execSQL("INSERT INTO EQUIPOS VALUES(3,'Equipo 3','logo3.png','Jugador3E3.png');");
        db.execSQL("INSERT INTO EQUIPOS VALUES(4,'Equipo 4','logo4.png','Jugador3E4.png');");
        db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(1,1,1);");
        db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(2,1,4);");
        db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(3,1,2);");
        db.execSQL("INSERT INTO USUARIO_EQUIPO VALUES(4,1,3);");

        db.execSQL("INSERT INTO ROLES VALUES(1,'atacante','es un jugador especializado en ataque');");
        db.execSQL("INSERT INTO ROLES VALUES(2,'defensa','su contundencia en la defensa es memorable');");
        db.execSQL("INSERT INTO ROLES VALUES(3,'chutador','cuanto mas chuta... mas inutil es');");

        db.execSQL("INSERT INTO HABILIDADES VALUES(1,'Fuerza','def fuerza');");
        db.execSQL("INSERT INTO HABILIDADES VALUES(2,'Defensa','def defensa');");
        db.execSQL("INSERT INTO HABILIDADES VALUES(3,'Habilidad','def habilidad');");
        db.execSQL("INSERT INTO HABILIDADES VALUES(4,'Resistencia','def resistencia');");
        db.execSQL("INSERT INTO HABILIDADES VALUES(5,'Ataque','def ataque');");

        db.execSQL("INSERT INTO OBJETOS VALUES(1,'Mina','Coloca este objeto en una posición determinada del campo, y el jugador rival que lo coja morirá.');");
        db.execSQL("INSERT INTO OBJETOS VALUES(2,'Beer','El jugador llama a un hooligan de su equipo que ayuda a hacer bloqueos hasta que el árbitro lo vea y lo expulse.');");
        db.execSQL("INSERT INTO OBJETOS VALUES(3,'Mario','Aparece Mario en el campo y quita todos los objetos y powerups que perjudiquen a su equipo.');");
        db.execSQL("INSERT INTO OBJETOS VALUES(4,'Cheerleader','El jugador coloca en el campo una cheerleader que distrae a todos los jugadores del equipo contrario que se encuentren cerca durante ese turno.');");
        db.execSQL("INSERT INTO OBJETOS VALUES(5,'The Hole','Coloca este objeto en una posición determinada del campo, y cuando un jugador rival pase por encima desaparecerá hasta que tu equipo consiga un punto o llegue el descanso.');");
        db.execSQL("INSERT INTO OBJETOS VALUES(6,'Cripple','Coloca este objeto en una posición determinada del camp, y el jugador rival que lo coja se verá ralentizado durante el resto del partido.');");

        int count = 1;
        for(int i : new int[]{1,2,3,4}) {
            for(String nom : new String[]{"Manu","Aitor","Victor","Victor M","Nicoleta","Suki","Aleix","Carles","Adria","Esther","Aureli","Ruben","Richi","La Sombra","Ivan"}){
                db.execSQL("INSERT INTO JUGADORES VALUES("+(count)+",'"+nom+"',NULL,NULL);");
                db.execSQL("INSERT INTO JUGADOR_EQUIPO VALUES("+count+","+i+");");
                db.execSQL("INSERT INTO JUGADOR_ROL VALUES('"+count+"',"+count+","+(Math.round(Math.random() * 2)+1)+");");
                for(int j : new int[]{1,2,3,4,5}) {
                    db.execSQL("INSERT INTO JUGADOR_HABILIDAD VALUES(" + count + "," + j + "," + Math.round(Math.random() * 100) + ");");
                }
                count++;
            }
        }
        backupDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        String[] tablas = new String[]{
            tbJugadores.TABLE,
            tbObjetos.TABLE,
            tbEquipos.TABLE,
            tbHabilidades.TABLE,
            tbUsuarios.TABLE,
            tbRoles.TABLE,
            tbUsuarioEquipo.TABLE,
            tbJugadorEquipo.TABLE,
            tbJugadorRol.TABLE,
            tbJugadorObjeto.TABLE,
            tbJugadorHabilidad.TABLE,
            tbExtras.TABLE,
            tbJugadorExtra.TABLE,
            tbPowerups.TABLE,
            tbHistorialPartido.TABLE,
            tbLiga.TABLE
        };
        for(String tab : tablas){
            db.execSQL("DROP TABLE IF EXISTS "+tab);
        }
        onCreate(db);
    }

    public static void backupDatabase() {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.uab.lis.rugby" +"/databases/"+nomBD;
        String backupDBPath = nomBD;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}