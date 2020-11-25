package org.izv.agenda.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    private TextView IdText,NombreText,ApellidosText,TelefonoText,FNacimientoText,LocalidadText,CalleText,NumeroText;

    public PersonaViewHolder(@NonNull View itemView) {
        super(itemView);
        this.IdText = itemView.findViewById(R.id.idPersona);
        this.NombreText = itemView.findViewById(R.id.tv_apellidos);
        this.ApellidosText = itemView.findViewById(R.id.tv_nombre);
        this.TelefonoText = itemView.findViewById(R.id.tv_telefono);
        this.FNacimientoText = itemView.findViewById(R.id.tv_fnacimiento);
        this.LocalidadText = itemView.findViewById(R.id.tv_localidad);
        this.CalleText = itemView.findViewById(R.id.tv_calle);
        this.NumeroText = itemView.findViewById(R.id.tv_numero);
    }

    //aquí se le asigna un valor al textview
    public void bind(Persona persona) {
        IdText.setText(String.valueOf(persona.getId()));
        NombreText.setText(persona.getNombre());
        ApellidosText.setText(persona.getApellidos());
        TelefonoText.setText("Telf.: "+persona.getTelefono());
        FNacimientoText.setText("F.Nacimiento: "+persona.getFNacimiento());
        LocalidadText.setText("Localidad: "+persona.getLocalidad());
        CalleText.setText("Calle: "+persona.getCalle());
        NumeroText.setText("Número: "+persona.getNumero());
    }

    //aquí se crea un layout del tipo item (inflate)
    static PersonaViewHolder create(ViewGroup parent, View view) {
        //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //view.setOnClickListener(this);
        return new PersonaViewHolder(view);
    }
}
