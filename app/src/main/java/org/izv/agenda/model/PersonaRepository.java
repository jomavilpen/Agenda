package org.izv.agenda.model;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.izv.agenda.AgendaApplication;
import org.izv.agenda.model.dao.PersonaDao;
import org.izv.agenda.model.entity.Persona;
import org.izv.agenda.model.room.AgendaDB;

import java.util.List;

public class PersonaRepository {

    //Dentro del constructor extraemos un nuevo objeto de tipo Context del objeto de ese mismo tipo
    //recibido. Esto es imprescindible para evitar fugas de memoria, como bien indica Android Studio.
    // Con esto, el problema queda resuelto, aunque Android Studio sigue marcándolo. De ahí que haya
    // añadido la anotación @SuppressLint("StaticFieldLeak").
    @SuppressLint("StaticFieldLeak")
    private static PersonaRepository sPersonaRepository;

    private AgendaDB db;
    private PersonaDao personaDao;
    private LiveData<List<Persona>> liveListaPersonas;

    //Al instanciar NotaDatabase he utilizado el método allowMainThreadQueries(). Este método permite
    // interactuar con la base de datos sin crear un hilo de ejecución paralelo (lo que es algo más
    // complejo). Como la base de datos es muy pequeña, no es necesario.
    public PersonaRepository(Context context){
        Context appContext = context.getApplicationContext();
        db = AgendaDB.getDB(context);
        personaDao = db.getPersonaDao();

        liveListaPersonas = personaDao.getAllLive();
    }

    //Esta clase es un singleton. Esto significa que solo va a existir una única instancia de
    // NotaLab; para asignar un objeto de tipo NotaLab a una referencia haremos una llamada a un
    // método estático que precisa un argumento: un objeto de tipo Context. Este método comprueba si
    // la única instancia de NotaLab existe ya; si no, la instancia.
    public static PersonaRepository get(Context context) {
        if (sPersonaRepository == null) {
            sPersonaRepository = new PersonaRepository(context);
        }
        return sPersonaRepository;
    }

    public LiveData<List<Persona>> getLiveListaPersonas() {
        return liveListaPersonas;
    }

    public Persona get(int id) {
        return personaDao.get(id);
    }

    public void insert(Persona persona) {
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personaDao.insert(persona);
            }
        });
    }

    public void delete(Persona persona){
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personaDao.delete(persona);
            }
        });
    }

    public void update(Persona persona){
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personaDao.update(persona);
            }
        });
    }
}
