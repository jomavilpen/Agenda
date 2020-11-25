package org.izv.agenda.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.agenda.model.entity.Persona;

import java.util.List;

@Dao
public interface PersonaDao {

    @Delete
    public void delete(Persona persona);

    @Query(value = "delete from persona")
    public void deleteAll();

    @Query("select * from persona")
    public List<Persona> getAll();

    @Query("select * from persona order by id")
    LiveData<List<Persona>> getAllLive();

    @Query("select * from persona where id = :id")
    Persona get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Persona persona);

    @Update
    public void update(Persona persona);
}
