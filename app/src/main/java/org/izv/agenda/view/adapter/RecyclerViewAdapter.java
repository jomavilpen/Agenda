package org.izv.agenda.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecicleViewAdapter";
    private Context contexto;
    private List<Persona> contactos;

    public RecyclerViewAdapter(Context contexto, List<Persona> contactos) {
        this.contactos = contactos;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "OnbindViewHolder called");
        holder.nombre.setText(contactos.get(position).getNombre());
        holder.apellidos.setText(contactos.get(position).getApellidos());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on "+ contactos.get(position));
                Toast.makeText(contexto, contactos.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView apellidos;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tv_apellidos);
            apellidos = itemView.findViewById(R.id.tv_nombre);
            parent_layout =  itemView.findViewById(R.id.parent_layout);
        }
    }
}
