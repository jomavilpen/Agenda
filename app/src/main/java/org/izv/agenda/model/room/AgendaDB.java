package org.izv.agenda.model.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.agenda.model.dao.PersonaDao;
import org.izv.agenda.model.entity.Persona;

@Database(entities = {Persona.class},version=1, exportSchema = false)
public abstract class AgendaDB extends RoomDatabase {

    public abstract PersonaDao getPersonaDao();

    private volatile static org.izv.agenda.model.room.AgendaDB INSTANCE;

    public static AgendaDB getDB(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), org.izv.agenda.model.room.AgendaDB.class,"agendadb").build();
        }
        return INSTANCE;
    }

}
