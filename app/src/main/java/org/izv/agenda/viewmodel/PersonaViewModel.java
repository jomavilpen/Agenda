package org.izv.agenda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.agenda.model.PersonaRepository;
import org.izv.agenda.model.entity.Persona;

import java.util.List;

public class PersonaViewModel extends AndroidViewModel {

    private PersonaRepository personaRepository;
    private LiveData<List<Persona>> livePersona;

    public PersonaViewModel(@NonNull Application application) {
        super(application);
        personaRepository = new PersonaRepository(application);
        livePersona = personaRepository.getLiveListaPersonas();
    }

    public LiveData<List<Persona>> getAllPersonas() {
        return livePersona;
    }

    public void insert(Persona persona) {
        personaRepository.insert(persona);
    }

    public void update(Persona persona){
        personaRepository.update(persona);
    }

    public void delete(Persona persona){
        personaRepository.delete(persona);
    }

    public Persona get(int id){
        return personaRepository.get(id);
    }
}
