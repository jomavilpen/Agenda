package org.izv.agenda.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;

public class PersonaAdapter extends ListAdapter<Persona, PersonaViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;

    public PersonaAdapter(@NonNull DiffUtil.ItemCallback<Persona> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        return PersonaViewHolder.create(parent,view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona current = getItem(position);
        holder.bind(current);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class PersonaDiff extends DiffUtil.ItemCallback<Persona> {

        @Override
        public boolean areItemsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}
